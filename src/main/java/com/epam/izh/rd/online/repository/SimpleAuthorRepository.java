package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (authors.length == 0) {
            authors = new Author[1];
            authors[0] = author;
            return true;
        } else {
            Author authorFound = null;
            for (Author a : authors) {
                authorFound = findByFullName(a.getName(), a.getLastName());
                if (authorFound == null) {
                    authors = Arrays.copyOf(authors, authors.length + 1);
                    authors[authors.length - 1] = authorFound;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        if (authors.length == 0) {
            return null;
        } else {
            for (Author author : authors) {
                if (author.getName().equals(name) && author.getLastName().equals(lastname)) {
                    return author;
                }
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (authors.length == 0) {
            return false;
        } else {
            for (int i = 0; i < authors.length; i++) {
                Author authorFound = findByFullName(authors[i].getName(), authors[i].getLastName());
                if (authorFound != null) {
                    if (i != authors.length - 1) {
                        authors[i] = authors[authors.length - 1];
                        authors[authors.length - 1] = null;
                        authors = Arrays.copyOfRange(authors, 0, authors.length - 1);
                        return true;
                    } else {
                        authors[authors.length - 1] = null;
                        authors = Arrays.copyOfRange(authors, 0, authors.length - 1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
