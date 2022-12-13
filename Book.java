public class Book {
    private final String name;
    private final String author;
    private final int publicationYear;

    public Book(String name, String author, int publicationYear) {
        this.name = name;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Book book = (Book) obj;
        return book.name.equals(name) && book.author.equals(author) && book.publicationYear == publicationYear;
    }

    @Override
    public int hashCode() { // Просто рандомная хэш-функция
        final int prime = 7;
        int result = 1;
        result = prime * result + name.hashCode();
        result = prime * result + publicationYear;
        result = prime * result + author.hashCode();
        return result;
    }
}
