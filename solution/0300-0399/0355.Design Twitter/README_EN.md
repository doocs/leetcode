# [355. Design Twitter](https://leetcode.com/problems/design-twitter)

## Description
<p>Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:</p>

<p>
<ol>
<li><b>postTweet(userId, tweetId)</b>: Compose a new tweet.</li>
<li><b>getNewsFeed(userId)</b>: Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.</li>
<li><b>follow(followerId, followeeId)</b>: Follower follows a followee.</li>
<li><b>unfollow(followerId, followeeId)</b>: Follower unfollows a followee.</li>
</ol>
</p>

<p><b>Example:</b>
<pre>
Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
</pre>
</p>


## Solutions


### Python3

```python

```

### Java

```java

```

### ...
```

```
