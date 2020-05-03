package DAODemo.service;

import DAODemo.dao.UserDAO;
import DAODemo.model.User;

public class UserService {

    private UserDAO userDao = new UserDAO();

    public User login(String username, String password) throws UserException {
        User user = userDao.findByUsername(username);
        if (user != null) {
            if (!user.getPassword().equals(password)) {
                throw new UserException("���벻��ȷ");
            } else {
                return user;
            }
        } else {
            throw new UserException("�û���������");
        }
    }

    public void AddUser(User user) throws Exception {
        User thisUser = userDao.findByUsername(user.getUsername());
        if (thisUser == null) {
            userDao.addUser(user);
        } else {
            throw new UserException("�û��Ѵ���");
        }

    }

}
