package com.bookbros.apis;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookJsonConvertModel {

	public class Author{
	    public String url;
	    public String name;
	    public String key;
	}

	public class Identifiers{
	    public List<String> librarything;
	    public List<String> isbn_10;
	    public List<String> lccn;
	    public List<String> oclc;
	    public List<String> openlibrary;
	}

	public class Classifications{
	    public List<String> lc_classifications;
	    public List<String> dewey_decimal_class;
	}

	public class Publisher{
	    public String name;
	}

	public class PublishPlace{
	    public String name;
	}

	public class Subject{
	    public String name;
	    public String url;
	}

	public class SubjectPeople{
	    public String name;
	    public String url;
	}

	public class Excerpt{
	    public String text;
	    public String comment;
	    public boolean first_sentence;
	}

	public class Cover{
	    public String small;
	    public String medium;
	    public String large;
	}

	public class Data{
	    public String url;
	    public String key;
	    public String title;
	    public String subtitle;
	    public List<Author> authors;
	    public int number_of_pages;
	    public String pagination;
	    public String by_statement;
	    public Identifiers identifiers;
	    public Classifications classifications;
	    public List<Publisher> publishers;
	    public List<PublishPlace> publish_places;
	    public String publish_date;
	    public List<Subject> subjects;
	    public List<SubjectPeople> subject_people;
	    public List<Excerpt> excerpts;
	    public String notes;
	    public Cover cover;
	}

	public class Language{
	    public String key;
	}

	public class Notes{
	    public String type;
	    public String value;
	}

	public class Created{
	    public String type;
	    public Date value;
	}

	public class LastModified{
	    public String type;
	    public Date value;
	}

	public class Work{
	    public String key;
	}

	public class Type{
	    public String key;
	}

	public class Details2{
	    public List<String> publishers;
	    public Identifiers identifiers;
	    public String subtitle;
	    public List<String> ia_box_id;
	    public List<String> isbn_10;
	    public List<int> covers;
	    public List<String> ia_loaded_id;
	    public List<String> lc_classifications;
	    public int latest_revision;
	    public String key;
	    public List<Author> authors;
	    public List<String> publish_places;
	    public List<Language> languages;
	    public String pagination;
	    public List<String> source_records;
	    public String title;
	    public List<String> dewey_decimal_class;
	    public Notes notes;
	    public int number_of_pages;
	    public Created created;
	    public String edition_name;
	    public List<String> lccn;
	    public List<String> subjects;
	    public String publish_date;
	    public String publish_country;
	    public LastModified last_modified;
	    public String by_statement;
	    public List<String> oclc_numbers;
	    public List<Work> works;
	    public Type type;
	    public int revision;
	    public String bib_key;
	    public String info_url;
	    public String preview;
	    public String preview_url;
	    public String thumbnail_url;
	    public Details details;
	}

	public class BooksOL673896M{
	    public List<String> isbns;
	    public List<Object> issns;
	    public List<String> lccns;
	    public List<String> oclcs;
	    public List<String> olids;
	    public List<String> publishDates;
	    public String recordURL;
	    public Data data;
	    public Details details;
	}

	public class Records{
	    @JsonProperty("/books/OL673896M") 
	    public BooksOL673896M booksOL673896M;
	}

	public class Root{
	    public Records records;
	    public List<Object> items;
	}
}
