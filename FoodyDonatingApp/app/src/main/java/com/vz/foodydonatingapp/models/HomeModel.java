package com.vz.foodydonatingapp.models;

public class HomeModel {
    int profileImg,moreImg,food1,food2,food3,love,chat,share;
    String name,minsAgo,writeSome,comments,sHare,countComment,countShare,countMin;

    public HomeModel(int profileImg, int moreImg, int food1, int food2, int food3,
                     int love, int chat, int share, String countComment, String countShare,
                     String countMin, String name, String minsAgo, String writeSome,
                     String comments, String sHare) {
        this.profileImg = profileImg;
        this.moreImg = moreImg;
        this.food1 = food1;
        this.food2 = food2;
        this.food3 = food3;
        this.love = love;
        this.chat = chat;
        this.share = share;
        this.countComment = countComment;
        this.countShare = countShare;
        this.countMin = countMin;
        this.name = name;
        this.minsAgo = minsAgo;
        this.writeSome = writeSome;
        this.comments = comments;
        this.sHare = sHare;
    }

    public int getProfileImg() {
        return profileImg;
    }

    public int getMoreImg() {
        return moreImg;
    }

    public int getFood1() {
        return food1;
    }

    public int getFood2() {
        return food2;
    }

    public int getFood3() {
        return food3;
    }

    public int getLove() {
        return love;
    }

    public int getChat() {
        return chat;
    }

    public int getShare() {
        return share;
    }

    public String getCountComment() {
        return countComment;
    }

    public String getCountShare() {
        return countShare;
    }

    public String getCountMin() {
        return countMin;
    }

    public String getName() {
        return name;
    }

    public String getMinsAgo() {
        return minsAgo;
    }

    public String getWriteSome() {
        return writeSome;
    }

    public String getComments() {
        return comments;
    }

    public String getsHare() {
        return sHare;
    }
}
