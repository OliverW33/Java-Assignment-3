package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageHandler {

    public static Background backgroundImage() {

        BackgroundImage myBI = new BackgroundImage(new Image("https://cdn-0.idownloadblog.com/wp-content/uploads/2020/10/Pacfic-Blue-iphone-wallpaper-idownloadblog-bgirija303-light-blue-gradient.png",600,900,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background appBackground = new Background(myBI);

        return appBackground;
    }

    public static ImageView footer() throws FileNotFoundException {
        Image imageBanner;
        ImageView imageViewBanner;

        imageBanner = new Image(new FileInputStream("images/Footer.jpg"));
        imageViewBanner = new ImageView(imageBanner);

        return imageViewBanner;
    }

    public static ImageView navbar() throws FileNotFoundException {
        Image imageBanner;
        ImageView imageViewBanner;

        imageBanner = new Image(new FileInputStream("images/DashNew5.jpg"));
        imageViewBanner = new ImageView(imageBanner);

        return imageViewBanner;
    }

    public static ImageView messagesButtonImage() throws FileNotFoundException {
        Image messageFeedImage;
        ImageView messageFeedImageView;

        messageFeedImage = new Image(new FileInputStream("images/MessageFeedNew3.jpg"));
        messageFeedImageView = new ImageView(messageFeedImage);

        return messageFeedImageView;
    }

    public static ImageView publishButtonImage() throws FileNotFoundException {
        Image publishImage;
        ImageView publishImageView;

        publishImage = new Image(new FileInputStream("images/PublishMessageNew.jpg"));
        publishImageView = new ImageView(publishImage);

        return publishImageView;
    }

    public static ImageView subscribersButtonImage() throws FileNotFoundException {
        Image subscribersImage;
        ImageView subscribersImageView;

        subscribersImage = new Image(new FileInputStream("images/ViewSubscribersNew2.jpg"));
        subscribersImageView = new ImageView(subscribersImage);

        return subscribersImageView;
    }

    public static ImageView searchButtonImage() throws FileNotFoundException {
        Image searchImage;
        ImageView searchImageView;

        searchImage = new Image(new FileInputStream("images/Search2.jpg"));
        searchImageView = new ImageView(searchImage);

        return searchImageView;
    }

    public static ImageView submitButtonImage() throws FileNotFoundException {
        Image submitImage;
        ImageView submitImageView;

        submitImage = new Image(new FileInputStream("images/Submit2.jpg"));
        submitImageView = new ImageView(submitImage);

        return submitImageView;
    }

    public static ImageView subscribeButtonImage() throws FileNotFoundException {

        Image subscribeImage;
        ImageView subscribeImageView;

        subscribeImage = new Image(new FileInputStream("images/Subscribe.jpg"));
        subscribeImageView = new ImageView(subscribeImage);

        return subscribeImageView;

    }

    public static ImageView unsubscribeButtonImage() throws FileNotFoundException {

        Image unsubscribeImage;
        ImageView unsubscribeImageView;

        unsubscribeImage = new Image(new FileInputStream("images/Unsubscribe.jpg"));
        unsubscribeImageView = new ImageView(unsubscribeImage);

        return unsubscribeImageView;

    }
}
