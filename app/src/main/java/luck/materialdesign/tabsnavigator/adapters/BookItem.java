package luck.materialdesign.tabsnavigator.adapters;

/**
 * Created by ahmed on 20/06/17.
 */

public class BookItem {

    String bookImageUrl = "", bookName = "";


    public BookItem(String bookImageUrl, String bookName){
        this.bookImageUrl = bookImageUrl;
        this.bookName = bookName;

    }


    public String getBookImageUrl() {
        return bookImageUrl;
    }

    public void setBookImageUrl(String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }





}
