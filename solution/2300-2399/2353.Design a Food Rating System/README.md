---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2353.Design%20a%20Food%20Rating%20System/README.md
rating: 1781
source: 第 303 场周赛 Q3
tags:
    - 设计
    - 数组
    - 哈希表
    - 字符串
    - 有序集合
    - 堆（优先队列）
---

<!-- problem:start -->

# [2353. 设计食物评分系统](https://leetcode.cn/problems/design-a-food-rating-system)

[English Version](/solution/2300-2399/2353.Design%20a%20Food%20Rating%20System/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计一个支持下述操作的食物评分系统：</p>

<ul>
	<li><strong>修改</strong> 系统中列出的某种食物的评分。</li>
	<li>返回系统中某一类烹饪方式下评分最高的食物。</li>
</ul>

<p>实现 <code>FoodRatings</code> 类：</p>

<ul>
	<li><code>FoodRatings(String[] foods, String[] cuisines, int[] ratings)</code> 初始化系统。食物由 <code>foods</code>、<code>cuisines</code> 和 <code>ratings</code> 描述，长度均为 <code>n</code> 。

    <ul>
    	<li><code>foods[i]</code> 是第 <code>i</code> 种食物的名字。</li>
    	<li><code>cuisines[i]</code> 是第 <code>i</code> 种食物的烹饪方式。</li>
    	<li><code>ratings[i]</code> 是第 <code>i</code> 种食物的最初评分。</li>
    </ul>
    </li>
    <li><code>void changeRating(String food, int newRating)</code> 修改名字为 <code>food</code> 的食物的评分。</li>
    <li><code>String highestRated(String cuisine)</code> 返回指定烹饪方式 <code>cuisine</code> 下评分最高的食物的名字。如果存在并列，返回 <strong>字典序较小</strong> 的名字。</li>

</ul>

<p>注意，字符串 <code>x</code> 的字典序比字符串 <code>y</code> 更小的前提是：<code>x</code> 在字典中出现的位置在 <code>y</code> 之前，也就是说，要么 <code>x</code> 是 <code>y</code> 的前缀，或者在满足&nbsp;<code>x[i] != y[i]</code> 的第一个位置 <code>i</code> 处，<code>x[i]</code> 在字母表中出现的位置在 <code>y[i]</code> 之前。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入</strong>
["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]
[[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]
<strong>输出</strong>
[null, "kimchi", "ramen", null, "sushi", null, "ramen"]

<strong>解释</strong>
FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]);
foodRatings.highestRated("korean"); // 返回 "kimchi"
                                    // "kimchi" 是分数最高的韩式料理，评分为 9 。
foodRatings.highestRated("japanese"); // 返回 "ramen"
                                      // "ramen" 是分数最高的日式料理，评分为 14 。
foodRatings.changeRating("sushi", 16); // "sushi" 现在评分变更为 16 。
foodRatings.highestRated("japanese"); // 返回 "sushi"
                                      // "sushi" 是分数最高的日式料理，评分为 16 。
foodRatings.changeRating("ramen", 16); // "ramen" 现在评分变更为 16 。
foodRatings.highestRated("japanese"); // 返回 "ramen"
                                      // "sushi" 和 "ramen" 的评分都是 16 。
                                      // 但是，"ramen" 的字典序比 "sushi" 更小。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>n == foods.length == cuisines.length == ratings.length</code></li>
	<li><code>1 &lt;= foods[i].length, cuisines[i].length &lt;= 10</code></li>
	<li><code>foods[i]</code>、<code>cuisines[i]</code> 由小写英文字母组成</li>
	<li><code>1 &lt;= ratings[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>foods</code> 中的所有字符串 <strong>互不相同</strong></li>
	<li>在对&nbsp;<code>changeRating</code> 的所有调用中，<code>food</code> 是系统中食物的名字。</li>
	<li>在对&nbsp;<code>highestRated</code> 的所有调用中，<code>cuisine</code> 是系统中 <strong>至少一种</strong> 食物的烹饪方式。</li>
	<li>最多调用 <code>changeRating</code> 和 <code>highestRated</code> <strong>总计</strong> <code>2 * 10<sup>4</sup></code> 次</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 有序集合

我们可以使用哈希表 $\textit{d}$ 来存储每种烹饪方式下的食物，其中键是烹饪方式，值是一个有序集合，有序集合的每个元素是一个二元组 $(\textit{rating}, \textit{food})$，按照评分从高到低排序，如果评分相同，则按照食物名字的字典序从小到大排序。

我们还可以使用哈希表 $\textit{g}$ 来存储每种食物的评分和烹饪方式。即 $\textit{g}[\textit{food}] = (\textit{rating}, \textit{cuisine})$。

在构造函数中，我们遍历 $\textit{foods}$、$\textit{cuisines}$ 和 $\textit{ratings}$，将每种食物的评分和烹饪方式存储到 $\textit{d}$ 和 $\textit{g}$ 中。

在 $\textit{changeRating}$ 函数中，我们首先获取食物 $\textit{food}$ 的原评分 $\textit{oldRating}$ 和烹饪方式 $\textit{cuisine}$，然后更新 $\textit{g}[\textit{food}]$ 的评分为 $\textit{newRating}$，并从 $\textit{d}[\textit{cuisine}]$ 中删除 $(\textit{oldRating}, \textit{food})$，并将 $(\textit{newRating}, \textit{food})$ 添加到 $\textit{d}[\textit{cuisine}]$ 中。

在 $\textit{highestRated}$ 函数中，我们直接返回 $\textit{d}[\textit{cuisine}]$ 的第一个元素的食物名字即可。

时间复杂度方面，构造函数的时间复杂度为 $O(n \log n)$，其中 $n$ 是食物的数量。其余操作的时间复杂度为 $O(\log n)$。空间复杂度为 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class FoodRatings:

    def __init__(self, foods: List[str], cuisines: List[str], ratings: List[int]):
        self.d = defaultdict(SortedList)
        self.g = {}
        for food, cuisine, rating in zip(foods, cuisines, ratings):
            self.d[cuisine].add((-rating, food))
            self.g[food] = (rating, cuisine)

    def changeRating(self, food: str, newRating: int) -> None:
        oldRating, cuisine = self.g[food]
        self.g[food] = (newRating, cuisine)
        self.d[cuisine].remove((-oldRating, food))
        self.d[cuisine].add((-newRating, food))

    def highestRated(self, cuisine: str) -> str:
        return self.d[cuisine][0][1]


# Your FoodRatings object will be instantiated and called as such:
# obj = FoodRatings(foods, cuisines, ratings)
# obj.changeRating(food,newRating)
# param_2 = obj.highestRated(cuisine)
```

#### Java

```java
class FoodRatings {
    private Map<String, TreeSet<Pair<Integer, String>>> d = new HashMap<>();
    private Map<String, Pair<Integer, String>> g = new HashMap<>();
    private final Comparator<Pair<Integer, String>> cmp = (a, b) -> {
        if (!a.getKey().equals(b.getKey())) {
            return b.getKey().compareTo(a.getKey());
        }
        return a.getValue().compareTo(b.getValue());
    };

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; ++i) {
            String food = foods[i], cuisine = cuisines[i];
            int rating = ratings[i];
            d.computeIfAbsent(cuisine, k -> new TreeSet<>(cmp)).add(new Pair<>(rating, food));
            g.put(food, new Pair<>(rating, cuisine));
        }
    }

    public void changeRating(String food, int newRating) {
        Pair<Integer, String> old = g.get(food);
        int oldRating = old.getKey();
        String cuisine = old.getValue();
        g.put(food, new Pair<>(newRating, cuisine));
        d.get(cuisine).remove(new Pair<>(oldRating, food));
        d.get(cuisine).add(new Pair<>(newRating, food));
    }

    public String highestRated(String cuisine) {
        return d.get(cuisine).first().getValue();
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
```

#### C++

```cpp
class FoodRatings {
public:
    FoodRatings(vector<string>& foods, vector<string>& cuisines, vector<int>& ratings) {
        for (int i = 0; i < foods.size(); ++i) {
            string food = foods[i], cuisine = cuisines[i];
            int rating = ratings[i];
            d[cuisine].insert({-rating, food});
            g[food] = {rating, cuisine};
        }
    }

    void changeRating(string food, int newRating) {
        auto [oldRating, cuisine] = g[food];
        g[food] = {newRating, cuisine};
        d[cuisine].erase({-oldRating, food});
        d[cuisine].insert({-newRating, food});
    }

    string highestRated(string cuisine) {
        return d[cuisine].begin()->second;
    }

private:
    unordered_map<string, set<pair<int, string>>> d;
    unordered_map<string, pair<int, string>> g;
};

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings* obj = new FoodRatings(foods, cuisines, ratings);
 * obj->changeRating(food,newRating);
 * string param_2 = obj->highestRated(cuisine);
 */
```

#### Go

```go
import (
	"github.com/emirpasic/gods/v2/trees/redblacktree"
)

type pair struct {
	rating int
	food   string
}

type FoodRatings struct {
	d map[string]*redblacktree.Tree[pair, struct{}]
	g map[string]pair
}

func Constructor(foods []string, cuisines []string, ratings []int) FoodRatings {
	d := make(map[string]*redblacktree.Tree[pair, struct{}])
	g := make(map[string]pair)

	for i, food := range foods {
		rating, cuisine := ratings[i], cuisines[i]
		g[food] = pair{rating, cuisine}

		if d[cuisine] == nil {
			d[cuisine] = redblacktree.NewWith[pair, struct{}](func(a, b pair) int {
				return cmp.Or(b.rating-a.rating, strings.Compare(a.food, b.food))
			})
		}
		d[cuisine].Put(pair{rating, food}, struct{}{})
	}

	return FoodRatings{d, g}
}

func (this *FoodRatings) ChangeRating(food string, newRating int) {
	p := this.g[food]
	t := this.d[p.food]

	t.Remove(pair{p.rating, food})
	t.Put(pair{newRating, food}, struct{}{})

	p.rating = newRating
	this.g[food] = p
}

func (this *FoodRatings) HighestRated(cuisine string) string {
	return this.d[cuisine].Left().Key.food
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * obj := Constructor(foods, cuisines, ratings);
 * obj.ChangeRating(food,newRating);
 * param_2 := obj.HighestRated(cuisine);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
