package vn.com.ntqsolution.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.com.ntqsolution.domain.BorrowedInformation;
import vn.com.ntqsolution.domain.User;
import vn.com.ntqsolution.request.BorrowRequest;
import vn.com.ntqsolution.request.UserRequest;
import vn.com.ntqsolution.service.BorrowedInformationService;
import vn.com.ntqsolution.service.UserService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SchedulingTask {

    @Autowired
    BorrowedInformationService borrowedInformationService;

    @Autowired
    UserService userService;

    @Scheduled(fixedRate = 1000 * 60)
    public void reportTimeBorrow() throws Exception {
//        List borrow muon :Scan bang thong tin muon sach
        //check ngày mượn / thời lượng mượn = 0 || < 1 thi delete thông tin để thu hồi sách
        // thời gian gian mượn >= 1 => list tạm thời // sort


        // update lại thời gian mượn sách trong bảng user
        //scan list tạm thời
        // stream lambda để tìm thời gian mượn max trong list tạm thời
        List<User> users = userService.getUsers();
        List<BorrowedInformation> list = borrowedInformationService.getAll();
        List<BorrowedInformation> tmp = new ArrayList<>();
        for (BorrowedInformation borrowedInformation : list) {
            User user = userService.getUser(borrowedInformation.getUserId());
            if (borrowedInformation.getBorrowTime() < 1) {
                System.out.println("User id: " + user.getId() + " ,name: " + user.getName() + " , da het han muon sach");
                borrowedInformationService.deleteById(borrowedInformation.getId());
                continue;
            }
            tmp.add(borrowedInformation);
        }
        tmp = tmp.stream().sorted(Comparator.comparingLong(BorrowedInformation::getBorrowDate).reversed())
                .collect(Collectors.toList());
        for (User user : users) {
            Optional<BorrowedInformation> optional = tmp.stream().filter(obj -> obj.getUserId().equals(user.getId())).findFirst();
            if (!optional.isPresent()) {
                System.out.println("User id: " + user.getId() + ", name: " + user.getName() + ", khong muon sach");
                user.setNewestBorrowDate(0);
                user.setBorrowTime(0);
                userService.editUser(new UserRequest(user));
                continue;
            }
            BorrowedInformation borrowedInformation = optional.get();
            user.setNewestBorrowDate(borrowedInformation.getBorrowDate());
            System.out.println("User id: " + user.getId() + ", name: " + user.getName() + ", con "
                    + borrowedInformation.getBorrowTime() + " ngay muon");
            int newBorrowTime = borrowedInformation.getBorrowTime() - 1;
            user.setBorrowTime(newBorrowTime);
            userService.editUser(new UserRequest(user));
            borrowedInformationService.edit(new BorrowRequest(borrowedInformation.getId(), borrowedInformation.getBookId(),
                    borrowedInformation.getUserId(), borrowedInformation.getAdminId(), newBorrowTime));
        }

    }
}
