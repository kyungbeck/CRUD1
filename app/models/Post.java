package models;

import play.data.validation.*; // for the @Constraints.Required
import java.util.HashSet;
import java.util.Set;

public class Post {
	private static Set<Post> posts;

	static {
		posts = new HashSet<Post>();
		posts.add(new Post(1, "첫번째 포스트", "첫번째 포스트 내용"));
		posts.add(new Post(2, "두번째 포스트", "두번째 포스트 내용"));
		posts.add(new Post(3, "세번째 포스트", "세번째 포스트 내용"));
		posts.add(new Post(4, "네번째 포스트", "네번째 포스트 내용"));
		posts.add(new Post(5, "다섯번째 포스트", "다섯번째 포스트 내용"));
	}
	
	@Constraints.Required
	public int postNum;
	@Constraints.Required
	public String title;
	public String content;


	public static Set<Post> findAll(){
		return new HashSet<Post>(posts);
	}

	public static Post findByPostNum(int postNum) {
		for(Post post : posts) {
			if(post.postNum==postNum) {
				return post;
			}
		}
		return null;
	}

	public static Set<Post> findByTitle(String title) {
		final Set<Post> results = new HashSet<Post>();
		for(Post post : posts) {
			if(post.title.toLowerCase().contains(title.toLowerCase())) {
				results.add(post);
			}
		}
		return results;
	}

	public static boolean remove(Post post) {
		return posts.remove(post);
	}

	public void save() {
		posts.remove(findByPostNum(this.postNum));
		posts.add(this);
	}

	public Post() {}

	public Post(int postNum, String title, String content) {
		this.postNum = postNum;
		this.title = title;
		this.content = content;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", title, content);
	}
}