class Twitter {
    class Data {
        int id, tweetId;

        public Data(int id, int tweetId) {
            this.id = id;
            this.tweetId = tweetId;
        }
    }

    private Map<Integer, List<Data>> posts;
    private Map<Integer, Set<Integer>> follows;
    private int id;

    /** Initialize your data structure here. */
    public Twitter() {
        posts = new HashMap<>();
        follows = new HashMap<>();
        id = 0;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!posts.containsKey(userId)) {
            posts.put(userId, new ArrayList<>());
        }
        posts.get(userId).add(new Data(id++, tweetId));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Data> queue = new PriorityQueue<>(10, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return Integer.compare(o2.id, o1.id);
            }
        });
        List<Data> ps = posts.get(userId);
        if (ps != null) {
            for (Data data : ps) {
                queue.offer(data);
            }
        }
        Set<Integer> fs = follows.get(userId);
        if (fs != null) {
            for (int f : fs) {
                ps = posts.get(f);
                if (ps != null) {
                    for (Data data : ps) {
                        queue.offer(data);
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 10 && !queue.isEmpty(); ++i) {
            res.add(queue.poll().tweetId);
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, intg followeeId) {
        if (followerId != followeeId) {
            if (!follows.containsKey(followerId)) {
                follows.put(followerId, new HashSet<>());
            }
            follows.get(followerId).add(followeeId);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (follows.containsKey(followerId)) {
            follows.get(followerId).remove(followeeId);
        }
    }
}
