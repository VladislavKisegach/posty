package ru.netology

import ru.netology.objects.common.Comment
import ru.netology.objects.common.Report

object WallService {
    val posts = mutableListOf<Post>()
    val comments = mutableListOf<Comment>()
    val reports = mutableListOf<Report>()

    fun add(post: Post): Post {
        posts.add(post)
        post.id = posts.size
        return posts.last()
    }

    fun update(post: Post): Boolean {
        return if (posts.find { it.id == post.id } != null) {
            posts[post.id - 1] = post.copy(
                text = post.text,
                friendsOnly = post.friendsOnly,
                comments = post.comments,
                likes = post.likes,
                reposts = post.reposts,
                views = post.views,
                postType = post.postType,
                canPin = post.canPin,
                canDelete = post.canDelete,
                canEdit = post.canEdit,
                isPinned = post.isPinned,
                markedAsAds = post.markedAsAds,
                isFavorite = post.isFavorite,
                donut = post.donut,
                postponedId = post.postponedId
            )
            true
        }
        else false
    }

    fun createComment(comment: Comment) {
        if (posts.find { it.id == comment.postId } != null) {
            comments.add(comment)
        }
        else throw PostNotFoundException()
    }

    fun reportComment(report: Report) {
        if (comments.find { it.ownerId == report.commentId } != null) {
            reports.add(report)
        } else throw CommentNotFoundException()
    }
}