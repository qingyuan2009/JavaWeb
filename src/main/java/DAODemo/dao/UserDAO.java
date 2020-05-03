package DAODemo.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import DAODemo.model.User;

public class UserDAO {

    // Class 转换为XML element
    public void toElement(User user, Element userNode) {
        userNode.addAttribute("username", user.getUsername());
        userNode.addAttribute("password", user.getPassword());
        userNode.addAttribute("sex", user.getSex());
        userNode.addAttribute("birthday", user.getBirthday());
        userNode.addAttribute("email", user.getEmail());
    }

    // XML element 转换为 class
    public User toBean(Element element) {
        User user = new User();
        user.setUsername(element.attributeValue("username"));
        user.setPassword(element.attributeValue("password"));
        user.setSex(element.attributeValue("sex"));
        user.setBirthday(element.attributeValue("birthday"));
        user.setEmail(element.attributeValue("email"));
        return user;
    }

    // 读取XML document
    private Document getDocument() {
        SAXReader reader = new SAXReader();
        Document doc;
        try {
            doc = reader.read("src/main/java/DAODemo/dao/user.xml");
        }
        catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return doc;
    }

    // save XML document
    private void saveDocument(Document doc) {
        OutputFormat format = new OutputFormat("\t", true);
        format.setTrimText(true); // 删除空白
        XMLWriter writer;
        try {
            writer = new XMLWriter(new FileOutputStream("src/main/java/DAODemo/dao/user.xml"), format);
            writer.write(doc);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Add User object to XML file
    public void addUser(User user) throws Exception {
        Document doc = getDocument();
        Element userEle = (Element) doc.selectSingleNode("//user[@username='" + user.getUsername() + "']");
        if (userEle == null) {
            Element root = doc.getRootElement();
            Element userNode = root.addElement("user");
            toElement(user, userNode);
            saveDocument(doc);
        }
    }

    // @Test
    public void TestAdd() throws Exception {
        User user = new User();
        user.setUsername("ABC");
        user.setPassword("125");
        user.setSex("male");
        user.setBirthday("1977");
        user.setEmail("efg.com");
        addUser(user);
    }

    // update
    public void updateUser(User user) {
        Document doc = getDocument();
        /*
         * Element root = doc.getRootElement();
         * List<Element> userList = root.elements("user");
         * for (Element element : userList) {
         * if (element.attributeValue("username").equals(user.getUsername())) {
         * toElement(user, element);
         * saveDocument(doc);
         * }
         * }
         */
        Element userEle = (Element) doc.selectSingleNode("//user[@username='" + user.getUsername() + "']");
        toElement(user, userEle);
        saveDocument(doc);

    }

    // @Test
    public void TestUpdate() throws Exception {
        User user = new User();
        user.setUsername("ABC");
        user.setPassword("333");
        user.setSex("male");
        user.setBirthday("1977");
        user.setEmail("rui.com");
        updateUser(user);
    }

    // delete
    public void deleteUser(String username) {
        Document doc = getDocument();
        Element root = doc.getRootElement();
        List<Element> userList = root.elements("user");
        for (Element element : userList) {
            if (element.attributeValue("username").equals(username)) {
                element.getParent().remove(element);
                saveDocument(doc);
            }
        }
    }

    // @Test
    public void TestDelete() throws Exception {
        deleteUser("ABC");
    }

    // search all
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Document doc = getDocument();
        Element root = doc.getRootElement();
        List<Element> userList = root.elements("user");
        for (Element element : userList) {
            users.add(toBean(element));
        }
        return users;
    }

    // @Test
    public void TestfindAll() throws Exception {
        List<User> users = findAll();
        for (User user : users) {
            System.out.println("Find all: " + user.toString());
        }
    }

    public User findByUsername(String username) {
        Document doc = getDocument();
        Element root = doc.getRootElement();
        List<Element> userList = root.elements("user");
        for (Element element : userList) {
            if (element.attributeValue("username").equals(username)) {
                return toBean(element);
            }
        }
        return null;
    }

    // @Test
    public void TestfindByUsername() throws Exception {
        User user = findByUsername("ABC");
        System.out.println("Find by username: " + user.toString());
    }

    public User findByEmail(String email) {
        Document doc = getDocument();
        Element root = doc.getRootElement();
        List<Element> userList = root.elements("user");
        for (Element element : userList) {
            if (element.attributeValue("email").equals(email)) {
                return toBean(element);
            }
        }
        return null;
    }

    // @Test
    public void TestfindByEmail() throws Exception {
        User user = findByEmail("efg.com");
        System.out.println("Find by email: " + user.toString());
    }

}
