---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1912.Design%20Movie%20Rental%20System/README.md
rating: 2181
source: 第 55 场双周赛 Q4
tags:
    - 设计
    - 数组
    - 哈希表
    - 有序集合
    - 堆（优先队列）
---

<!-- problem:start -->

# [1912. 设计电影租借系统](https://leetcode.cn/problems/design-movie-rental-system)

[English Version](/solution/1900-1999/1912.Design%20Movie%20Rental%20System/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有一个电影租借公司和 <code>n</code> 个电影商店。你想要实现一个电影租借系统，它支持查询、预订和返还电影的操作。同时系统还能生成一份当前被借出电影的报告。</p>

<p>所有电影用二维整数数组 <code>entries</code> 表示，其中 <code>entries[i] = [shop<sub>i</sub>, movie<sub>i</sub>, price<sub>i</sub>]</code> 表示商店 <code>shop<sub>i</sub></code> 有一份电影 <code>movie<sub>i</sub></code> 的拷贝，租借价格为 <code>price<sub>i</sub></code> 。每个商店有 <strong>至多一份</strong> 编号为 <code>movie<sub>i</sub></code> 的电影拷贝。</p>

<p>系统需要支持以下操作：</p>

<ul>
	<li><strong>Search：</strong>找到拥有指定电影且 <strong>未借出</strong> 的商店中 <strong>最便宜的 5 个</strong> 。商店需要按照 <strong>价格</strong> 升序排序，如果价格相同，则 <code>shop<sub>i</sub></code> <strong>较小</strong> 的商店排在前面。如果查询结果少于 5 个商店，则将它们全部返回。如果查询结果没有任何商店，则返回空列表。</li>
	<li><strong>Rent：</strong>从指定商店借出指定电影，题目保证指定电影在指定商店 <strong>未借出</strong> 。</li>
	<li><strong>Drop：</strong>在指定商店返还 <strong>之前已借出</strong> 的指定电影。</li>
	<li><strong>Report：</strong>返回 <strong>最便宜的 5 部已借出电影</strong> （可能有重复的电影 ID），将结果用二维列表 <code>res</code> 返回，其中 <code>res[j] = [shop<sub>j</sub>, movie<sub>j</sub>]</code> 表示第 <code>j</code> 便宜的已借出电影是从商店 <code>shop<sub>j</sub></code> 借出的电影 <code>movie<sub>j</sub></code> 。<code>res</code> 中的电影需要按 <strong>价格</strong> 升序排序；如果价格相同，则<strong> </strong><code>shop<sub>j</sub></code> <strong>较小</strong> 的排在前面；如果仍然相同，则 <code>movie<sub>j</sub></code> <strong>较小 </strong>的排在前面。如果当前借出的电影小于 5 部，则将它们全部返回。如果当前没有借出电影，则返回一个空的列表。</li>
</ul>

<p>请你实现 <code>MovieRentingSystem</code> 类：</p>

<ul>
	<li><code>MovieRentingSystem(int n, int[][] entries)</code> 将 <code>MovieRentingSystem</code> 对象用 <code>n</code> 个商店和 <code>entries</code> 表示的电影列表初始化。</li>
	<li><code>List&lt;Integer&gt; search(int movie)</code> 如上所述，返回 <strong>未借出</strong> 指定 <code>movie</code> 的商店列表。</li>
	<li><code>void rent(int shop, int movie)</code> 从指定商店 <code>shop</code> 借出指定电影 <code>movie</code> 。</li>
	<li><code>void drop(int shop, int movie)</code> 在指定商店 <code>shop</code> 返还之前借出的电影 <code>movie</code> 。</li>
	<li><code>List&lt;List&lt;Integer&gt;&gt; report()</code> 如上所述，返回最便宜的 <strong>已借出</strong> 电影列表。</li>
</ul>

<p><strong>注意：</strong>测试数据保证 <code>rent</code> 操作中指定商店拥有 <strong>未借出 </strong>的指定电影，且 <code>drop</code> 操作指定的商店 <strong>之前已借出</strong> 指定电影。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["MovieRentingSystem", "search", "rent", "rent", "report", "drop", "search"]
[[3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]], [1], [0, 1], [1, 2], [], [1, 2], [2]]
<strong>输出：</strong>
[null, [1, 0, 2], null, null, [[0, 1], [1, 2]], null, [0, 1]]

<strong>解释：</strong>
MovieRentingSystem movieRentingSystem = new MovieRentingSystem(3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]);
movieRentingSystem.search(1);  // 返回 [1, 0, 2] ，商店 1，0 和 2 有未借出的 ID 为 1 的电影。商店 1 最便宜，商店 0 和 2 价格相同，所以按商店编号排序。
movieRentingSystem.rent(0, 1); // 从商店 0 借出电影 1 。现在商店 0 未借出电影编号为 [2,3] 。
movieRentingSystem.rent(1, 2); // 从商店 1 借出电影 2 。现在商店 1 未借出的电影编号为 [1] 。
movieRentingSystem.report();   // 返回 [[0, 1], [1, 2]] 。商店 0 借出的电影 1 最便宜，然后是商店 1 借出的电影 2 。
movieRentingSystem.drop(1, 2); // 在商店 1 返还电影 2 。现在商店 1 未借出的电影编号为 [1,2] 。
movieRentingSystem.search(2);  // 返回 [0, 1] 。商店 0 和 1 有未借出的 ID 为 2 的电影。商店 0 最便宜，然后是商店 1 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 3 * 10<sup>5</sup></code></li>
	<li><code>1 <= entries.length <= 10<sup>5</sup></code></li>
	<li><code>0 <= shop<sub>i</sub> < n</code></li>
	<li><code>1 <= movie<sub>i</sub>, price<sub>i</sub> <= 10<sup>4</sup></code></li>
	<li>每个商店 <strong>至多</strong> 有一份电影 <code>movie<sub>i</sub></code> 的拷贝。</li>
	<li><code>search</code>，<code>rent</code>，<code>drop</code> 和 <code>report</code> 的调用 <strong>总共</strong> 不超过 <code>10<sup>5</sup></code> 次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：有序集合

我们定义一个有序集合 $\textit{available}$，其中 $\textit{available}[movie]$ 存储所有未借出的电影 $movie$ 的商店列表，列表中的元素为 $(\textit{price}, \textit{shop})$，并按照 $\textit{price}$ 升序排序，如果 $\textit{price}$ 相同，则按照 $\textit{shop}$ 升序排序。

另外定义一个哈希表 $\textit{price\_map}$，其中 $\textit{price\_map}[f(\textit{shop}, \textit{movie})]$ 存储商店 $\textit{shop}$ 中电影 $\textit{movie}$ 的租借价格。

我们还定义一个有序集合 $\textit{rented}$，其中存储所有已借出的电影，元素为 $(\textit{price}, \textit{shop}, \textit{movie})$，并按照 $\textit{price}$ 升序排序，如果 $\textit{price}$ 相同，则按照 $\textit{shop}$ 升序排序，如果 $\textit{shop}$ 也相同，则按照 $\textit{movie}$ 升序排序。

对于 $\text{MovieRentingSystem}(n, \text{entries})$ 操作，我们遍历 $\text{entries}$，将每个商店的电影信息加入到 $\textit{available}$ 和 $\textit{price\_map}$ 中。时间复杂度为 $O(m \log m)$，其中 $m$ 是 $\text{entries}$ 的长度。

对于 $\text{search}(\text{movie})$ 操作，我们返回 $\textit{available}[\text{movie}]$ 中前 5 个商店的编号。时间复杂度为 $O(1)$。

对于 $\text{rent}(\text{shop}, \text{movie})$ 操作，我们从 $\textit{available}[\text{movie}]$ 中移除 $(\textit{price}, \textit{shop})$，并将 $(\textit{price}, \textit{shop}, \textit{movie})$ 加入到 $\textit{rented}$ 中。时间复杂度为 $O(\log m)$。

对于 $\text{drop}(\text{shop}, \text{movie})$ 操作，我们从 $\textit{rented}$ 中移除 $(\textit{price}, \textit{shop}, \textit{movie})$，并将 $(\textit{price}, \textit{shop})$ 加入到 $\textit{available}[\text{movie}]$ 中。时间复杂度为 $O(\log m)$。

对于 $\text{report}()$ 操作，我们返回 $\textit{rented}$ 中前 5 个已借出电影的商店编号和电影编号。时间复杂度为 $O(1)$。

空间复杂度为 $O(m)$。其中 $m$ 是 $\text{entries}$ 的长度。

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
