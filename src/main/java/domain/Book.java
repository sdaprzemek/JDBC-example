package domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Book {

    private Long id;
    private String title;
    private Boolean hasHardCover;
    private Integer numberOfPages;
    private LocalDate releaseDate;

    public void setReleaseDate(LocalDate now) {

    }

    public String getTitle() {
        return null;
    }

    public int getNumberOfPages() {
        return 0;
    }

    public boolean getHasHardCover() {
        return false;
    }

    public String getReleaseDate() {
        return null;
    }

    public long getId() {
        return 0;
    }

    public void setTitle(String new_book) {

    }

    public void setHasHardCover(boolean b) {

    }

    public void setNumberOfPages(int i) {

    }

    public void setId(long aLong) {

    }
}
