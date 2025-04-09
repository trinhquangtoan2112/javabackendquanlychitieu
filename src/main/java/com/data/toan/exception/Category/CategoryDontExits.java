package com.data.toan.exception.Category;

public class CategoryDontExits extends RuntimeException  {
   
    public CategoryDontExits(String mess) {
        super(mess);
    }

    public CategoryDontExits(String mess, Throwable cause) {
        super(mess, cause);
    }
    
}