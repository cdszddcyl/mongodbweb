package com.mongodb.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.WriteResult;
import com.mongodb.model.Person;
import com.mongodb.service.MongoService;
import com.mongodb.util.PageInfo;
import com.mongodb.util.Parameter;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private MongoService service;
	

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public @ResponseBody String indexData() {
		Person person=new Person("张三", 20);
		person.setId("5");
		service.insert(person);
		PageInfo page=new PageInfo();
		page.setCurrentPage(1);
		page.setPageSize(10);
		
		Query query =new Query();
		Criteria cri=new Criteria();
		cri.andOperator(Criteria.where("age").is(20));
		cri.orOperator(Criteria.where("name").is("张三"),Criteria.where("name").is("李四2"));
		query.addCriteria(cri);
		
		PageInfo info= service.findPageInfo(page,query, Person.class);
		for (Object obj: info.getList()) {
			Person p=(Person)obj;
			System.out.println(p.getId()+":"+p.getName()+":"+p.getAge());
		}
	
		return "成功";
	}

}
