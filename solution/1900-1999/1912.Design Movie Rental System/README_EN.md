---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1912.Design%20Movie%20Rental%20System/README_EN.md
rating: 2181
source: Biweekly Contest 55 Q4
tags:
    - Design
    - Array
    - Hash Table
    - Ordered Set
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [1912. Design Movie Rental System](https://leetcode.com/problems/design-movie-rental-system)

[中文文档](/solution/1900-1999/1912.Design%20Movie%20Rental%20System/README.md)

## Description

<!-- description:start -->

<p>You have a movie renting company consisting of <code>n</code> shops. You want to implement a renting system that supports searching for, booking, and returning movies. The system should also support generating a report of the currently rented movies.</p>

<p>Each movie is given as a 2D integer array <code>entries</code> where <code>entries[i] = [shop<sub>i</sub>, movie<sub>i</sub>, price<sub>i</sub>]</code> indicates that there is a copy of movie <code>movie<sub>i</sub></code> at shop <code>shop<sub>i</sub></code> with a rental price of <code>price<sub>i</sub></code>. Each shop carries <strong>at most one</strong> copy of a movie <code>movie<sub>i</sub></code>.</p>

<p>The system should support the following functions:</p>

<ul>
	<li><strong>Search</strong>: Finds the <strong>cheapest 5 shops</strong> that have an <strong>unrented copy</strong> of a given movie. The shops should be sorted by <strong>price</strong> in ascending order, and in case of a tie, the one with the <strong>smaller </strong><code>shop<sub>i</sub></code> should appear first. If there are less than 5 matching shops, then all of them should be returned. If no shop has an unrented copy, then an empty list should be returned.</li>
	<li><strong>Rent</strong>: Rents an <strong>unrented copy</strong> of a given movie from a given shop.</li>
	<li><strong>Drop</strong>: Drops off a <strong>previously rented copy</strong> of a given movie at a given shop.</li>
	<li><strong>Report</strong>: Returns the <strong>cheapest 5 rented movies</strong> (possibly of the same movie ID) as a 2D list <code>res</code> where <code>res[j] = [shop<sub>j</sub>, movie<sub>j</sub>]</code> describes that the <code>j<sup>th</sup></code> cheapest rented movie <code>movie<sub>j</sub></code> was rented from the shop <code>shop<sub>j</sub></code>. The movies in <code>res</code> should be sorted by <strong>price </strong>in ascending order, and in case of a tie, the one with the <strong>smaller </strong><code>shop<sub>j</sub></code> should appear first, and if there is still tie, the one with the <strong>smaller </strong><code>movie<sub>j</sub></code> should appear first. If there are fewer than 5 rented movies, then all of them should be returned. If no movies are currently being rented, then an empty list should be returned.</li>
</ul>

<p>Implement the <code>MovieRentingSystem</code> class:</p>

<ul>
	<li><code>MovieRentingSystem(int n, int[][] entries)</code> Initializes the <code>MovieRentingSystem</code> object with <code>n</code> shops and the movies in <code>entries</code>.</li>
	<li><code>List&lt;Integer&gt; search(int movie)</code> Returns a list of shops that have an <strong>unrented copy</strong> of the given <code>movie</code> as described above.</li>
	<li><code>void rent(int shop, int movie)</code> Rents the given <code>movie</code> from the given <code>shop</code>.</li>
	<li><code>void drop(int shop, int movie)</code> Drops off a previously rented <code>movie</code> at the given <code>shop</code>.</li>
	<li><code>List&lt;List&lt;Integer&gt;&gt; report()</code> Returns a list of cheapest <strong>rented</strong> movies as described above.</li>
</ul>

<p><strong>Note:</strong> The test cases will be generated such that <code>rent</code> will only be called if the shop has an <strong>unrented</strong> copy of the movie, and <code>drop</code> will only be called if the shop had <strong>previously rented</strong> out the movie.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;MovieRentingSystem&quot;, &quot;search&quot;, &quot;rent&quot;, &quot;rent&quot;, &quot;report&quot;, &quot;drop&quot;, &quot;search&quot;]
[[3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]], [1], [0, 1], [1, 2], [], [1, 2], [2]]
<strong>Output</strong>
[null, [1, 0, 2], null, null, [[0, 1], [1, 2]], null, [0, 1]]

<strong>Explanation</strong>
MovieRentingSystem movieRentingSystem = new MovieRentingSystem(3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]);
movieRentingSystem.search(1);  // return [1, 0, 2], Movies of ID 1 are unrented at shops 1, 0, and 2. Shop 1 is cheapest; shop 0 and 2 are the same price, so order by shop number.
movieRentingSystem.rent(0, 1); // Rent movie 1 from shop 0. Unrented movies at shop 0 are now [2,3].
movieRentingSystem.rent(1, 2); // Rent movie 2 from shop 1. Unrented movies at shop 1 are now [1].
movieRentingSystem.report();   // return [[0, 1], [1, 2]]. Movie 1 from shop 0 is cheapest, followed by movie 2 from shop 1.
movieRentingSystem.drop(1, 2); // Drop off movie 2 at shop 1. Unrented movies at shop 1 are now [1,2].
movieRentingSystem.search(2);  // return [0, 1]. Movies of ID 2 are unrented at shops 0 and 1. Shop 0 is cheapest, followed by shop 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= entries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= shop<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= movie<sub>i</sub>, price<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>Each shop carries <strong>at most one</strong> copy of a movie <code>movie<sub>i</sub></code>.</li>
	<li>At most <code>10<sup>5</sup></code> calls <strong>in total</strong> will be made to <code>search</code>, <code>rent</code>, <code>drop</code> and <code>report</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Ordered Set

We define an ordered set $\textit{available}$, where $\textit{available}[movie]$ stores a list of all shops that have not rented out the movie $movie$. Each element in the list is $(\textit{price}, \textit{shop})$, sorted in ascending order by $\textit{price}$, and if prices are equal, by $\textit{shop}$ in ascending order.

Additionally, we define a hash map $\textit{price\_map}$, where $\textit{price\_map}[f(\textit{shop}, \textit{movie})]$ stores the rental price of movie $\textit{movie}$ in shop $\textit{shop}$.

We also define an ordered set $\textit{rented}$, which stores all rented movies as $(\textit{price}, \textit{shop}, \textit{movie})$, sorted in ascending order by $\textit{price}$, then by $\textit{shop}$, and if both are equal, by $\textit{movie}$.

For the $\text{MovieRentingSystem}(n, \text{entries})$ operation, we iterate through $\text{entries}$ and add each shop's movie information to both $\textit{available}$ and $\textit{price\_map}$. The time complexity is $O(m \log m)$, where $m$ is the length of $\text{entries}$.

For the $\text{search}(\text{movie})$ operation, we return the shop IDs of the first 5 shops in $\textit{available}[\text{movie}]$. The time complexity is $O(1)$.

For the $\text{rent}(\text{shop}, \text{movie})$ operation, we remove $(\textit{price}, \textit{shop})$ from $\textit{available}[\text{movie}]$ and add $(\textit{price}, \textit{shop}, \textit{movie})$ to $\textit{rented}$. The time complexity is $O(\log m)$.

For the $\text{drop}(\text{shop}, \text{movie})$ operation, we remove $(\textit{price}, \textit{shop}, \textit{movie})$ from $\textit{rented}$ and add $(\textit{price}, \textit{shop})$ back to $\textit{available}[\text{movie}]$. The time complexity is $O(\log m)$.

For the $\text{report}()$ operation, we return the shop and movie IDs of the first 5 rented movies in $\textit{rented}$. The time complexity is $O(1)$.

The space complexity is $O(m)$, where $m$ is the length of $\text{entries}$.

<!-- tabs:start -->

#### Python3

```python
class MovieRentingSystem:

    def __init__(self, n: int, entries: List[List[int]]):
        self.available = defaultdict(lambda: SortedList())
        self.price_map = {}
        for shop, movie, price in entries:
            self.available[movie].add((price, shop))
            self.price_map[self.f(shop, movie)] = price
        self.rented = SortedList()

    def search(self, movie: int) -> List[int]:
        return [shop for _, shop in self.available[movie][:5]]

    def rent(self, shop: int, movie: int) -> None:
        price = self.price_map[self.f(shop, movie)]
        self.available[movie].remove((price, shop))
        self.rented.add((price, shop, movie))

    def drop(self, shop: int, movie: int) -> None:
        price = self.price_map[self.f(shop, movie)]
        self.rented.remove((price, shop, movie))
        self.available[movie].add((price, shop))

    def report(self) -> List[List[int]]:
        return [[shop, movie] for _, shop, movie in self.rented[:5]]

    def f(self, shop: int, movie: int) -> int:
        return shop << 30 | movie


# Your MovieRentingSystem object will be instantiated and called as such:
# obj = MovieRentingSystem(n, entries)
# param_1 = obj.search(movie)
# obj.rent(shop,movie)
# obj.drop(shop,movie)
# param_4 = obj.report()
```

#### Java

```java
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
```

#### C++

```cpp
class MovieRentingSystem {
private:
    unordered_map<int, set<pair<int, int>>> available; // movie -> {(price, shop)}
    unordered_map<long long, int> priceMap;
    set<tuple<int, int, int>> rented; // {(price, shop, movie)}

    long long f(int shop, int movie) {
        return ((long long) shop << 30) | movie;
    }

public:
    MovieRentingSystem(int n, vector<vector<int>>& entries) {
        for (auto& e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            available[movie].insert({price, shop});
            priceMap[f(shop, movie)] = price;
        }
    }

    vector<int> search(int movie) {
        vector<int> res;
        if (!available.count(movie)) {
            return res;
        }
        int cnt = 0;
        for (auto& [price, shop] : available[movie]) {
            res.push_back(shop);
            if (++cnt == 5) {
                break;
            }
        }
        return res;
    }

    void rent(int shop, int movie) {
        int price = priceMap[f(shop, movie)];
        available[movie].erase({price, shop});
        rented.insert({price, shop, movie});
    }

    void drop(int shop, int movie) {
        int price = priceMap[f(shop, movie)];
        rented.erase({price, shop, movie});
        available[movie].insert({price, shop});
    }

    vector<vector<int>> report() {
        vector<vector<int>> res;
        int cnt = 0;
        for (auto& [price, shop, movie] : rented) {
            res.push_back({shop, movie});
            if (++cnt == 5) {
                break;
            }
        }
        return res;
    }
};

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem* obj = new MovieRentingSystem(n, entries);
 * vector<int> param_1 = obj->search(movie);
 * obj->rent(shop,movie);
 * obj->drop(shop,movie);
 * vector<vector<int>> param_4 = obj->report();
 */
```

#### Go

```go
type MovieRentingSystem struct {
	available map[int]*treeset.Set // movie -> (price, shop)
	priceMap  map[int64]int
	rented    *treeset.Set // (price, shop, movie)
}

func Constructor(n int, entries [][]int) MovieRentingSystem {
	// comparator for (price, shop)
	cmpAvail := func(a, b any) int {
		x := a.([2]int)
		y := b.([2]int)
		if x[0] != y[0] {
			return x[0] - y[0]
		}
		return x[1] - y[1]
	}
	// comparator for (price, shop, movie)
	cmpRented := func(a, b any) int {
		x := a.([3]int)
		y := b.([3]int)
		if x[0] != y[0] {
			return x[0] - y[0]
		}
		if x[1] != y[1] {
			return x[1] - y[1]
		}
		return x[2] - y[2]
	}

	mrs := MovieRentingSystem{
		available: make(map[int]*treeset.Set),
		priceMap:  make(map[int64]int),
		rented:    treeset.NewWith(cmpRented),
	}

	for _, e := range entries {
		shop, movie, price := e[0], e[1], e[2]
		if _, ok := mrs.available[movie]; !ok {
			mrs.available[movie] = treeset.NewWith(cmpAvail)
		}
		mrs.available[movie].Add([2]int{price, shop})
		mrs.priceMap[f(shop, movie)] = price
	}

	return mrs
}

func (this *MovieRentingSystem) Search(movie int) []int {
	res := []int{}
	if _, ok := this.available[movie]; !ok {
		return res
	}
	it := this.available[movie].Iterator()
	it.Begin()
	cnt := 0
	for it.Next() && cnt < 5 {
		pair := it.Value().([2]int)
		res = append(res, pair[1])
		cnt++
	}
	return res
}

func (this *MovieRentingSystem) Rent(shop int, movie int) {
	price := this.priceMap[f(shop, movie)]
	this.available[movie].Remove([2]int{price, shop})
	this.rented.Add([3]int{price, shop, movie})
}

func (this *MovieRentingSystem) Drop(shop int, movie int) {
	price := this.priceMap[f(shop, movie)]
	this.rented.Remove([3]int{price, shop, movie})
	this.available[movie].Add([2]int{price, shop})
}

func (this *MovieRentingSystem) Report() [][]int {
	res := [][]int{}
	it := this.rented.Iterator()
	it.Begin()
	cnt := 0
	for it.Next() && cnt < 5 {
		t := it.Value().([3]int)
		res = append(res, []int{t[1], t[2]})
		cnt++
	}
	return res
}

func f(shop, movie int) int64 {
	return (int64(shop) << 30) | int64(movie)
}

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * obj := Constructor(n, entries);
 * param_1 := obj.Search(movie);
 * obj.Rent(shop,movie);
 * obj.Drop(shop,movie);
 * param_4 := obj.Report();
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
