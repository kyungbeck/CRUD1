package controllers;

import models.Post;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.blog.list;

import java.util.Set;

public class Blog extends Controller {
	public static Result list(){
		Set<Post> posts = Post.findAll();
		return ok(list.render(posts));
	}

	public static Result newPost() {
		return TODO;
	}

	public static Result details(int postNum) {
		return TODO;
	}

	public static Result save() {
		return TODO;
	}
}