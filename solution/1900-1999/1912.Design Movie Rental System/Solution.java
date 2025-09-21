class MovieRentingSystem {
    private Map<Integer, TreeSet<int[]>> available = new HashMap<>();
    private Map<Long, Integer> priceMap = new HashMap<>();
    private TreeSet<int[]> rented = new TreeSet<>((a, b) -> {
        if (a[0] != b[0]) {
            return a[0] - b[0];
        }
        if (a[1] != b[1]) {
            return a[1] - b[1];
        }
        return a[2] - b[2];
    });

    public MovieRentingSystem(int n, int[][] entries) {
        for (int[] entry : entries) {
            int shop = entry[0], movie = entry[1], price = entry[2];
            available
                .computeIfAbsent(movie, k -> new TreeSet<>((a, b) -> {
                    if (a[0] != b[0]) {
                        return a[0] - b[0];
                    }
                    return a[1] - b[1];
                }))
                .add(new int[] {price, shop});
            priceMap.put(f(shop, movie), price);
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (!available.containsKey(movie)) {
            return res;
        }
        int cnt = 0;
        for (int[] item : available.get(movie)) {
            res.add(item[1]);
            if (++cnt == 5) {
                break;
            }
        }
        return res;
    }

    public void rent(int shop, int movie) {
        int price = priceMap.get(f(shop, movie));
        available.get(movie).remove(new int[] {price, shop});
        rented.add(new int[] {price, shop, movie});
    }

    public void drop(int shop, int movie) {
        int price = priceMap.get(f(shop, movie));
        rented.remove(new int[] {price, shop, movie});
        available.get(movie).add(new int[] {price, shop});
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        int cnt = 0;
        for (int[] item : rented) {
            res.add(Arrays.asList(item[1], item[2]));
            if (++cnt == 5) {
                break;
            }
        }
        return res;
    }

    private long f(int shop, int movie) {
        return ((long) shop << 30) | movie;
    }
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */
