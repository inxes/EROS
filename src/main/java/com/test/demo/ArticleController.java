package com.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {

    @Autowired
    private ArticleProperties articleProperties;

    @Autowired
    private TestRepository testRepository;

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String showArticle() {
        return "show Comment";
    }

    @RequestMapping(value = "/getArticleConfig",method = RequestMethod.GET)
    public String getArticleConfig() {
        return articleProperties.getConfig();
    }

    @RequestMapping(value = "/getArticleArray",method = RequestMethod.GET)
    public String getArticleArray() {
        return articleProperties.getArray();
    }

    @GetMapping(value = "/getArticleList")
    public List<test> testList(){
        return testRepository.findAll();
    }

    /**
     * 返回一个对象
     * @param name
     * @param create_time
     * @return Object
     */
    @PostMapping(value = "/addArticle")
    public test addArticle(@RequestParam("name") String name,
                             @RequestParam("create_time")Integer create_time
    ){
        test test_article = new test();
        test_article.setCreate_time(create_time);
        test_article.setName(name);
        return testRepository.save(test_article);
    }


    @GetMapping(value = "/article/{id}")
    public Optional<test> getArticle(@PathVariable("id") Integer id){
        test tests = new test();
        tests.setId(id);
        Example<test> example = Example.of(tests);
        return testRepository.findOne(example);
    }
//
//    @PutMapping(value = "article/{id}")
//
//    @DeleteMapping(value = "article/{id}")
}
