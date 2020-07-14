package com.reckue.post.exceptions.models.tag;

import com.reckue.post.exceptions.ModelAlreadyExistsException;

/**
 * Class TagAlreadyExistException is responsible for throwing
 * exception when the created Tag already exists in database.
 *
 * @author Artur Magomedov
 */
public class TagAlreadyExistException extends ModelAlreadyExistsException {

    private final String message;

    public TagAlreadyExistException() {
        message = "Tag Already Exist";
    }

    public TagAlreadyExistException(String id) {
        message = "Tag by id " + id + " already exist";
    }
}
