package com.gemini.personalcapital.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ArticleTest {
    private final String title = "Personal Capital is Ten Years New";
    private final String comments = "https://www.personalcapital.com/blog/personal-capital-news/personal-capital-brand/#comments";
    private final String pubDate = "Tue, 29 Oct 2019 12:10:22 +0000";
    private final String category = "Personal Capital News";
    private final String description = "October marks our ten year anniversary, and we’re incredibly grateful to everyone who uses and loves our tools, our wealth management clients, and the entire Personal Capital team for an amazing decade. We couldn’t have done it without you. Because of this milestone, we thought it was the best time to introduce you to some [&#8230;";
    private final String imageUrl = "https://www.personalcapital.com/blog/wp-content/uploads/2019/10/BlogHeaderAnnouncement-1.jpg";
    private final String link = "https://www.personalcapital.com/blog/retirement-planning/self-employed-retirement-plans/";

    private final ArticleItemList mediaEntityList = new ArticleItemList();


    @Mock
    Article newsEntity;

    @Mock
    ArticleItemList entityList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(newsEntity.getTitle()).thenReturn(title);
        Mockito.when(newsEntity.getComments()).thenReturn(comments);
        Mockito.when(newsEntity.getPubDate()).thenReturn(pubDate);
        Mockito.when(newsEntity.getCategory()).thenReturn(category);
        Mockito.when(newsEntity.getDescription()).thenReturn(description);
        Mockito.when(newsEntity.getImageUrl()).thenReturn(imageUrl);
        Mockito.when(newsEntity.getLink()).thenReturn(link);
    }

    @Test
    public void testArticleTitle() {
        Mockito.when(newsEntity.getTitle()).thenReturn(title);
    }

    @Test
    public void testArticleComments() {
        Mockito.when(newsEntity.getComments()).thenReturn(comments);
    }

    @Test
    public void testArticlePubDate() {
        Mockito.when(newsEntity.getPubDate()).thenReturn(pubDate);
    }

    @Test
    public void testArticleCategory() {
        Mockito.when(newsEntity.getCategory()).thenReturn(category);
    }

    @Test
    public void testArticleDescription() {
        Mockito.when(newsEntity.getDescription()).thenReturn(description);
    }

    @Test
    public void testArticleImageUrl() {
        Mockito.when(newsEntity.getImageUrl()).thenReturn(imageUrl);
    }

    @Test
    public void testArticleLink() {
        Mockito.when(newsEntity.getLink()).thenReturn(link);
    }

    @Test
    public void testArticleTitleIncorrect(){
        Mockito.when(newsEntity.getTitle()).thenReturn(title);
        Assert.assertNotEquals("Title",newsEntity.getTitle());
    }

    @Test
    public void testArticleCommentsIncorrect(){
        Mockito.when(newsEntity.getComments()).thenReturn(comments);
        Assert.assertNotEquals("Comments",newsEntity.getComments());
    }

    @Test
    public void testArticlePubDateIncorrect(){
        Mockito.when(newsEntity.getPubDate()).thenReturn(pubDate);
        Assert.assertNotEquals("29-02-2019",newsEntity.getPubDate());
    }

    @Test
    public void testArticleCategoryIncorrect(){
        Mockito.when(newsEntity.getCategory()).thenReturn(category);
        Assert.assertNotEquals("Category",newsEntity.getCategory());
    }

    @Test
    public void testArticleDescriptionIncorrect(){
        Mockito.when(newsEntity.getDescription()).thenReturn(description);
        Assert.assertNotEquals("Description",newsEntity.getDescription());
    }

    @Test
    public void testArticleImageUrlIncorrect(){
        Mockito.when(newsEntity.getImageUrl()).thenReturn(imageUrl);
        Assert.assertNotEquals("www.google.com",newsEntity.getImageUrl());
    }

    @Test
    public void testArticleLinkIncorrect(){
        Mockito.when(newsEntity.getLink()).thenReturn(link);
        Assert.assertNotEquals("www.google.com",newsEntity.getLink());
    }

    @After
    public void tearDown() throws Exception {
        newsEntity =null;
    }
}