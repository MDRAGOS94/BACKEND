package com.traistarudragos.blogapp.rest;

import com.traistarudragos.blogapp.filter.SwearFilter;
import com.traistarudragos.blogapp.model.Article;
import com.traistarudragos.blogapp.model.Author;
import com.traistarudragos.blogapp.model.Comment;
import com.traistarudragos.blogapp.model.Rate;
import com.traistarudragos.blogapp.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleAPI {

    @Resource
    protected ArticleRepository articleRepository;

    // ######################################################################### GET METHODS :

    /**
     * get all articles
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Article> getArticles() {
        return articleRepository.findAll();
    }

    /**
     * get an article by id
     *
     * @param idArticle
     * @return
     */
    @RequestMapping(value = "/{idArticle}", method = RequestMethod.GET)
    public
    @ResponseBody
    Article getArticleById(@PathVariable("idArticle") String idArticle) {
        return articleRepository.findOne(idArticle);
    }

    /**
     * get author for an article
     *
     * @param idArticle
     * @return
     */
    @RequestMapping(value="/{idArticle}/author", method = RequestMethod.GET)
    public @ResponseBody Author getAuthor(@PathVariable("idArticle") String idArticle) {
        return articleRepository.findOne(idArticle).getAuthor();
    }

    /**
     * get all rates for an article
     *
     * @param idArticle
     * @return
     */
    @RequestMapping(value = "/{idArticle}/rates", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Rate> getAllRatesForAnArticle(@PathVariable("idArticle") String idArticle) {
        return articleRepository.findOne(idArticle).getListOfRates();
    }

    /**
     * get all comments for an article
     *
     * @param idArticle
     * @return
     */
    @RequestMapping(value = "/{idArticle}/comments", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Comment> getAllCommentsForAnArticle(@PathVariable("idArticle") String idArticle) {
        return articleRepository.findOne(idArticle).getListOfComments();
    }

    /**
     * get rate for an article
     *
     * @param idArticle
     * @param idRate
     * @return
     */
    @RequestMapping(value = "/{idArticle}/rates/{idRate}", method = RequestMethod.GET)
    public
    @ResponseBody
    Rate getRateForAnArticle(@PathVariable("idArticle") String idArticle
            , @PathVariable("idRate") int idRate) {
        if (idRate <= articleRepository.findOne(idArticle).getListOfRates().size() - 1) {
            return articleRepository.findOne(idArticle).getListOfRates().get(idRate);
        } else {
            return null;
        }
    }

    /**
     * get comment from an article
     *
     * @param idArticle
     * @param idComment
     * @return
     */
    @RequestMapping(value = "/{idArticle}/comments/{idComment}", method = RequestMethod.GET)
    public
    @ResponseBody
    Comment getCommentForAnArticle(@PathVariable("idArticle") String idArticle
            , @PathVariable("idComment") int idComment) {
        if (idComment <= articleRepository.findOne(idArticle).getListOfComments().size() - 1) {
            return articleRepository.findOne(idArticle).getListOfComments().get(idComment);
        } else {
            return null;
        }
    }

    // ######################################################################### POST METHODS :

    /**
     * post an article
     *
     * @param article
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    Boolean postArticle(@RequestBody Article article) {
        articleRepository.save(article);

        return true;
    }

    /**
     * post a rate
     *
     * @param rate
     * @param idArticle
     * @return
     */
    @RequestMapping(value = "/{idArticle}/rates/", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    Boolean postRateToArticle(@RequestBody Rate rate, @PathVariable("idArticle") String idArticle) {
        Article article = articleRepository.findOne(idArticle);
        article.getListOfRates().add(rate);
        articleRepository.save(article);

        return true;
    }

    /**
     * post a comment
     *
     * @param comment
     * @param idArticle
     * @return
     */
    @RequestMapping(value = "/{idArticle}/comments/", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    Boolean postCommentToArticle(@RequestBody Comment comment, @PathVariable("idArticle") String idArticle) {
        Article article = articleRepository.findOne(idArticle);

        String filteredComment = SwearFilter.filter(comment.getText());
        comment.setText(filteredComment);

        article.getListOfComments().add(comment);
        articleRepository.save(article);

        return true;
    }

    // ######################################################################### PUT METHODS :

    /**
     * put an article
     *
     * @param article
     * @param idArticle
     * @return
     */
    @RequestMapping(value = "/{idArticle}/", method = RequestMethod.PUT, consumes = "application/json")
    public
    @ResponseBody
    Boolean putArticle(@RequestBody Article article, @PathVariable("idArticle") String idArticle) {
        Article articleToModify = articleRepository.findOne(idArticle);
        articleToModify.setTitle(article.getTitle());
        articleToModify.setContent(article.getContent());
        articleToModify.setAuthor(article.getAuthor());
        //articleToModify.setListOfRates(article.getListOfRates());
        //articleToModify.setListOfComments(article.getListOfComments());
        articleRepository.save(articleToModify);

        return true;
    }

    /**
     * put an author
     *
     * @param author
     * @param idArticle
     * @return
     */
    @RequestMapping(value = "/{idArticle}/author/", method = RequestMethod.PUT, consumes = "application/json")
    public
    @ResponseBody
    Boolean putAuthorToArticle(@RequestBody Author author, @PathVariable("idArticle") String idArticle) {
        Article article = articleRepository.findOne(idArticle);
        article.setAuthor(author);
        articleRepository.save(article);

        return true;
    }

    /**
     * put a rate
     *
     * @param rate
     * @param idArticle
     * @param idRate
     * @return
     */
    @RequestMapping(value = "/{idArticle}/rates/{idRate}/", method = RequestMethod.PUT, consumes = "application/json")
    public
    @ResponseBody
    Boolean putRateToArticle(@RequestBody Rate rate, @PathVariable("idArticle") String idArticle
            , @PathVariable("idRate") int idRate) {
        Article article = articleRepository.findOne(idArticle);
        article.getListOfRates().set(idRate, rate);
        articleRepository.save(article);

        return true;
    }

    /**
     * put a comment
     *
     * @param comment
     * @param idArticle
     * @param idComment
     * @return
     */
    @RequestMapping(value = "/{idArticle}/comments/{idComment}/", method = RequestMethod.PUT, consumes = "application/json")
    public
    @ResponseBody
    Boolean putCommentToArticle(@RequestBody Comment comment, @PathVariable("idArticle") String idArticle
            , @PathVariable("idComment") int idComment) {
        Article article = articleRepository.findOne(idArticle);
        article.getListOfComments().set(idComment, comment);
        articleRepository.save(article);

        return true;
    }

    // ######################################################################### DELETE METHODS :

    /**
     * delete an article
     *
     * @param idArticle
     * @return
     */
    @RequestMapping(value = "/{idArticle}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Boolean deleteArticleById(@PathVariable("idArticle") String idArticle) {
        articleRepository.delete(idArticle);

        return true;
    }

    /**
     * delete rate for an article
     *
     * @param idArticle
     * @param idRate
     * @return
     */
    @RequestMapping(value = "/{idArticle}/rates/{idRate}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Boolean deleteNoteForAnArticle(@PathVariable("idArticle") String idArticle
            , @PathVariable("idRate") int idRate) {
        Article article = articleRepository.findOne(idArticle);
        article.getListOfRates().remove(idRate);
        articleRepository.save(article);

        return true;
    }

    /**
     * delete comment for an article
     *
     * @param idArticle
     * @param idComment
     * @return
     */
    @RequestMapping(value = "/{idArticle}/comments/{idComment}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Boolean deleteCommentForAnArticle(@PathVariable("idArticle") String idArticle
            , @PathVariable("idComment") int idComment) {
        Article article = articleRepository.findOne(idArticle);
        article.getListOfComments().remove(idComment);
        articleRepository.save(article);

        return true;
    }

}
