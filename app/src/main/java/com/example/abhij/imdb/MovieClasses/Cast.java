package com.example.abhij.imdb.MovieClasses;

/**
 * Created by abhij on 16-04-2018.
 */

public class Cast {

        public String cast_id;
        public String character;
        public String credit_id;
        public int id;
        public String name;
        public String profile_path;


        public Cast(String cast_id, String character, String credit_id, int id, String name, String profile_path) {
                this.cast_id = cast_id;
                this.character = character;
                this.credit_id = credit_id;
                this.id = id;
                this.name = name;
                this.profile_path = profile_path;
        }

        public String getCast_id() {
                return cast_id;
        }

        public void setCast_id(String cast_id) {
                this.cast_id = cast_id;
        }

        public String getCharacter() {
                return character;
        }

        public void setCharacter(String character) {
                this.character = character;
        }

        public String getCredit_id() {
                return credit_id;
        }

        public void setCredit_id(String credit_id) {
                this.credit_id = credit_id;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getProfile_path() {
                return profile_path;
        }

        public void setProfile_path(String profile_path) {
                this.profile_path = profile_path;
        }
}

