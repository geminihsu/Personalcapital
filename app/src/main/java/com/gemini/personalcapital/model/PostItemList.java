package com.gemini.personalcapital.model;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PostItemList extends ArrayList<Post> {

    @Override
    public boolean add(Post post) {

        if (post == null) {
            return false;
        }

        // if the game is already present in the array
        if (contains(post)) {
            return false;
        }

        return super.add(post);
    }

    @Override
    public boolean remove(@Nullable Object o) {
        if (o == null) {
            return false;
        }

        if (o instanceof Post) {
            Post post = (Post) o;
            for (int i = 0; i < this.size(); ++i) {
                if (this.get(i).equals(post)) {
                    super.remove(i);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean contains(@Nullable Object o) {
        if (o == null) {
            return false;
        }

        if (o instanceof Post) {
            for (Post post : this) {
                if (post.equals(o)) {
                    return true;
                }
            }
        }

        return false;
    }
}
