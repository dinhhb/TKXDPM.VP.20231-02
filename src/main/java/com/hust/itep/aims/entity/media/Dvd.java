package com.hust.itep.aims.entity.media;

import java.util.Date;

public class Dvd extends Media {

   private String dvdType;
   private String director;
   private int runtime;
   private String studio;
   private String language;
   private String subtitles;
   private Date releasedDate;
   private String filmType;

   public Dvd(Integer id, String category, int price, int value, String title, String description, int quantity, Date importDate, Boolean rushOrderSupport, String barcode, String productDimension, String imageUrl, String dvdType, String director, int runtime, String studio, String language, String subtitles, Date releasedDate, String filmType) {
       super(id, category, price, value, title, description, quantity, importDate, rushOrderSupport, barcode, productDimension, imageUrl);
       this.dvdType = dvdType;
       this.director = director;
       this.runtime = runtime;
       this.studio = studio;
       this.language = language;
       this.subtitles = subtitles;
       this.releasedDate = releasedDate;
       this.filmType = filmType;
   }

    public Dvd(Integer id, String category, int price, int value, String title, String description, int quantity, Date importDate, String barcode, String productDimension, String imageUrl, String dvdType, String director, int runtime, String studio, String language, String subtitles, Date releasedDate, String filmType) {
        super(id, category, price, value, title, description, quantity, importDate, barcode, productDimension, imageUrl);
        this.dvdType = dvdType;
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.language = language;
        this.subtitles = subtitles;
        this.releasedDate = releasedDate;
        this.filmType = filmType;
    }

   public Dvd(){
       super();
   }

    public Dvd(Media media, String studio, String filmType, String type, String language, String director, String subtitles, int runtime, Date publicationDate) {
        super(media.getId(), media.getCategory(), media.getPrice(),
                media.getValue(), media.getTitle(),  media.getDescription(),
                media.getQuantity(), media.getImportDate(),  media.getRushOrderSupport(),
                media.getProductDimension(), media.getBarcode(), media.getImageUrl());
        this.studio = studio;
        this.filmType = filmType;
        this.dvdType = type;
        this.language = language;
        this.director = director;
        this.subtitles = subtitles;
        this.runtime = runtime;
        this.releasedDate = publicationDate;
    }

    public String getDvdType() {
       return dvdType;
   }

   public void setDvdType(String dvdType) {
       this.dvdType = dvdType;
   }

   public String getDirector() {
       return director;
   }

   public void setDirector(String director) {
       this.director = director;
   }

   public int getRuntime() {
       return runtime;
   }

   public void setRuntime(int runtime) {
       this.runtime = runtime;
   }

   public String getStudio() {
       return studio;
   }

   public void setStudio(String studio) {
       this.studio = studio;
   }

   public String getLanguage() {
       return language;
   }

   public void setLanguage(String language) {
       this.language = language;
   }

   public String getSubtitles() {
       return subtitles;
   }

   public void setSubtitles(String subtitles) {
       this.subtitles = subtitles;
   }

   public Date getReleasedDate() {
       return releasedDate;
   }

   public void setReleasedDate(Date releasedDate) {
       this.releasedDate = releasedDate;
   }

   public String getFilmType() {
       return filmType;
   }

   public void setFilmType(String filmType) {
       this.filmType = filmType;
   }
}
