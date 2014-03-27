package controllers;

import play.data.*;

import models.Post;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.blog.list;
import views.html.blog.details;

import java.util.Set;

public class Blog extends Controller {
	private static final Form<Post> postForm = Form.form(Post.class);

	public static Result list(){
		Set<Post> posts = Post.findAll();
		return ok(list.render(posts));
	}

	public static Result newPost() {
		return ok(details.render(postForm));
	}

	public static Result details(int postNum) {
		final Post post = Post.findByPostNum(postNum);
		if(post==null) {
			return notFound(String.format("Post %s does not exist.", post));
		}

		Form<Post> filledForm = postForm.fill(post);
		return ok(details.render(filledForm));
	}


/*	public static Result save() {
		Form<Post> boundForm = postForm.bindFromRequest();
		Post post = boundForm.get();
		post.save();
		return ok(String.format("Saved post %s", post));
	}
*/

	public static Result save() {
		Form<Post> boundForm = postForm.bindFromRequest();
		if(boundForm.hasErrors()){
			flash("error", "Please correct the form below");
			return badRequest(details.render(boundForm));
		}

		Post post = boundForm.get();
		post.save();

		flash("success", String.format("Successfully added post %s", post));

		return redirect(routes.Blog.list());
	}
}