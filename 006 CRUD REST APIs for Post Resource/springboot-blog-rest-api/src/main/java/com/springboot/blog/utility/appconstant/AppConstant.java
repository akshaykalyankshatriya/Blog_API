package com.springboot.blog.utility.appconstant;

public class AppConstant {

    private AppConstant() {
    }

    // API Routes Path
    public static final String BASE_API_ROUTES="/api/v1";
    public static final String POST_POSTMAPPING="/createPost";
    public static final String POST_GETMAPPING="/getAllPosts";
    public static final String POST_GETBYID="/getById/{id}";
    public static final String POST_UPDATEBYID="/updatePostById/{id}";
    public static final String POST_DELETEMAPPING="/deletePostById/{id}";
    // Status
    public static final String STATUS_SUCCESS ="Success";
    public static final String STATUS_FAILURE="Failure";

    // Paginationg & Sorting


    // Error Message

    // Constant for Unit Test Case


    public static  final  String EMPTY= "";


}
