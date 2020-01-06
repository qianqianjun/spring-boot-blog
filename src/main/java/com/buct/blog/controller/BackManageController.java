package com.buct.blog.controller;
import com.buct.blog.domain.Article;
import com.buct.blog.domain.ArticleAndCategory;
import com.buct.blog.domain.Category;
import com.buct.blog.domain.Comment;
import com.buct.blog.domain.User;
import com.buct.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.processor.comment.ICommentStructureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;


/**
 * @author 高谦
 * 实现后台管理系统的相关功能
 */
@Controller
public class BackManageController {
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    FileService fileService;
    @Autowired
    CommentService commentService;

    /**
     * 后台管理页面数据准备接口
     * @param request 用于获取Session来判断是否登录
     * @return 返回模板页面
     */
    @GetMapping("/manage")
    public String manage(HttpServletRequest request,Map<String,Object> map){
        HttpSession session=request.getSession();
        if(session.getAttribute("user")==null){
            return "redirect:/manage/login";
        }
        User user=userService.getDefaultUser();
        map.put("user",user);
        return "backmanage/index";
    }

    /**
     * 用于注销登录
     * @param request 用于获取 session
     * @return 返回登录页面
     */
    @GetMapping("/manage/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.setAttribute("user",null);
        return "redirect:/manage/login";
    }

    /**
     * 后台文章管理界面
     * @param request 获取用户的信息
     * @param map 前台数据传递
     * @return 返回文章管理页面
     */
    @GetMapping("/manage/article")
    public String manageArticle(HttpServletRequest request,Map<String,Object> map){
        User user=userService.getDefaultUser();
        map.put("user",user);
        ArrayList<ArticleAndCategory> arrayList = (ArrayList<ArticleAndCategory>) articleService.getAllPublishArticlesAndCategory();
        map.put("publishArticles",arrayList);
        System.out.println(arrayList);
        ArrayList<ArticleAndCategory> arrayList1 =
                (ArrayList<ArticleAndCategory>) articleService.getAllUnpublishArticles();
        map.put("unpublishArticles",arrayList1);
        ArrayList<ArticleAndCategory> arrayList2 =
                (ArrayList<ArticleAndCategory>) articleService.getAllDeleteArticles();
        map.put("deleteArticles",arrayList2);
        return "backmanage/articleManage";
    }


    @GetMapping("/manage/category")
    public String manageCategory(HttpServletRequest request,Map<String,Object> map){
        User user=(User) request.getSession().getAttribute("user");
        map.put("user",user);
        ArrayList<Category> arrayList = (ArrayList<Category>) categoryService.getAllCategories();
        map.put("categories",arrayList);
        System.out.println(arrayList);
        return "backmanage/categoryManage";
    }
    /**
     * 写文章索引页面
     * @param request 获取session
     * @param map 用于前台参数传递
     * @return 返回写文章的页面
     */
    @GetMapping("/manage/write")
    public String write(HttpServletRequest request,Map<String,Object>map){
        User user=userService.getDefaultUser();
        map.put("user",user);
        ArrayList<Category> categories=(ArrayList<Category>) categoryService.getCategoriesLimits(1000);
        map.put("categories",categories);
        return "backmanage/write";
    }

    /**
     * 返回用户资料修改界面
     * @param request 用户获取用户信息
     * @param map 用于前台参数传递
     * @return 返回用户修改信息的界面
     */
    @GetMapping("/manage/profile")
    public String profile(HttpServletRequest request,Map<String,Object>map){
        User user=userService.getDefaultUser();
        map.put("user",user);
        return "backmanage/profile";
    }

    /**
     * 修改用户的头像
     * @param file 上传的文件
     * @return 返回文件存储在服务器目录的名称
     */
    @PostMapping("/manage/chanagePic")
    @ResponseBody
    public String changePic(@RequestParam("file") MultipartFile file){
        try{
            String url="/upload/"+fileService.storeFile(file);
            userService.changePic(url);
            return  url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改用户基本信息
     * @param mail 邮箱
     * @param phone 电话
     * @param company 公司
     * @param position 位置
     * @param resume 简历文章的 id
     * @return 返回修改后的信息
     */
    @PostMapping("/manage/updateBasic")
    @ResponseBody
    public User updateBasic(@RequestParam("mail") String mail,
                              @RequestParam("phone") String phone,
                              @RequestParam("company") String company,
                              @RequestParam("resume") Integer resume,
                              @RequestParam("position") String position){
        User user=new User();
        user.setCompany(company);
        user.setEmail(mail);
        user.setPhone(phone);
        user.setPosition(position);
        user.setResume(resume);
        userService.updateBasic(user);
        return user;
    }

    /**
     * 修改用户的其它信息
     * @param csdn csdn 博客地址
     * @param github github 地址
     * @return 返回修改之后的信息
     */
    @PostMapping("/manage/updateOther")
    @ResponseBody
    public User updateOther(@RequestParam("csdn") String csdn,
                            @RequestParam("github") String github){
        User user=new User();
        user.setGithub(github);
        user.setCsdn(csdn);
        userService.updateOther(user);
        return user;
    }


    /**
     * 设置轮播界面的接口
     * @param map 前台数据传送器
     * @return 设置轮播的界面
     */
    @GetMapping("/manage/banner")
    public String banner(Map<String,Object> map){
        ArrayList<Article> articles=(ArrayList<Article>) articleService.getArticlesWithCategory();
        map.put("articles",articles);
        User user=userService.getDefaultUser();
        map.put("user",user);
        return "backmanage/banner";
    }

    /**
     * 用于将文章设置为轮播文章
     * @param imgurl 轮播的图片
     * @param id 文章的id
     * @return 返回 article 相关信息
     */
    @PostMapping("/manage/setBanner")
    @ResponseBody
    public Article setBanner(@RequestParam("imgurl") String imgurl,
                             @RequestParam("id") Integer id){
        Article article=new Article();
        article.setImgurl(imgurl);
        article.setId(id);
        articleService.setBanner(article);
        return article;
    }

    /**
     * 取消文章的轮播
     * @param id 文章id
     * @return 文章相关信息
     */
    @PostMapping("/manage/cancleBanner")
    @ResponseBody
    public Article cancleBanner(@RequestParam("id") Integer id){
        articleService.cancleBanner(id);
        Article article=new Article();
        article.setId(id);
        return article;
    }

    /**
     * 评论管理界面
     * @param map 前台参数传递器
     * @return 返回所有的评论
     */
    @GetMapping("/manage/comment")
    public String CommentManage(Map<String,Object> map) {
        User user = userService.getDefaultUser();
        map.put("user", user);
        ArrayList<Comment> all = (ArrayList<Comment>)
                commentService.getAllComment();

        ArrayList<Comment> finish=new ArrayList<>();
        ArrayList<Comment> unfinish=new ArrayList<>();
        for(int i=0;i<all.size();i++){
            if(all.get(i).getReply()==null){
                unfinish.add(all.get(i));
            }else{
                finish.add(all.get(i));
            }
        }
        System.out.println(all.size());
        map.put("all", all);
        map.put("finish",finish);
        map.put("unfinish",unfinish);
        return "backmanage/commentManage";
    }


    @GetMapping("/manage/changeArticle")
    public String changeArticle(HttpServletRequest request,
                                @RequestParam("id") Integer id,
                                Map<String,Object>map) {
        User user = userService.getDefaultUser();
        map.put("user", user);
        Article article = articleService.getArticleById(id);
        map.put("article", article);
        ArrayList<Category> categories = (ArrayList<Category>) categoryService.getCategoriesLimits(1000);
        map.put("categories", categories);
        return "backmanage/fix";
    }

    /**
     * write by 高谦
     * @param id
     * @param reply
     * @return
     */
    @PostMapping("/manage/fixComment")
    public String fixComment(@RequestParam("id") Integer id,
                             @RequestParam("reply") String reply){
        commentService.fixComment(id,reply);
        return "redirect:/manage/comment";
    }

    /**
     * write by 高谦
     * @param id
     * @return
     */
    @GetMapping("/manage/delComment")
    public String delComment(@RequestParam("id") Integer id){
        commentService.delComment(id);
        return "redirect:/manage/comment";
    }

}
