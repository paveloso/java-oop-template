package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook schoolBook) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = schoolBook;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int foundCount = 0;
        for (SchoolBook sb : schoolBooks) {
            if (name.equals(sb.getName())) {
                foundCount++;
            }
        }
        if (foundCount == 0) {
            return new SchoolBook[0];
        } else {
            SchoolBook[] foundedBooks = new SchoolBook[foundCount];
            for (SchoolBook schoolBook : schoolBooks) {
                if (name.equals(schoolBook.getName())) {
                    foundedBooks[foundCount - 1] = schoolBook;
                    foundCount--;
                }
            }
            return foundedBooks;
        }
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] booksToRemove = findByName(name);
        if (booksToRemove.length == 0) {
            return false;
        }
        int arrayLength = schoolBooks.length;
        for (int i = 0; i < arrayLength; i++) {
            for (int j = 0; j < booksToRemove.length; j++) {
                if (schoolBooks[i].getName().equals(booksToRemove[j].getName())) {
                    SchoolBook temp = schoolBooks[arrayLength - 1];
                    schoolBooks[arrayLength - 1] = null;
                    schoolBooks[i] = temp;
                    arrayLength--;
                }
            }
        }
        schoolBooks = Arrays.copyOfRange(schoolBooks, 0, schoolBooks.length - booksToRemove.length);

        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
