package by.htp.library.bean;

public class Book {

	private String author;
	private String bookName;
	private String cover;

	public Book() {
	}

	public Book(String author, String bookName, String cover) {
		this.author = author;
		this.bookName = bookName;
		this.cover = cover;
	}

	public String getAuthor() {
		return author;
	}

	public String getBookName() {
		return bookName;
	}

	public String getCover() {
		return cover;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		result = prime * result + ((cover == null) ? 0 : cover.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (cover == null) {
			if (other.cover != null)
				return false;
		} else if (!cover.equals(other.cover))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", bookName=" + bookName + ", cover=" + cover + "]";
	}

}