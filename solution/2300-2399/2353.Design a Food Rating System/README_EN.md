---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2353.Design%20a%20Food%20Rating%20System/README_EN.md
rating: 1781
source: Weekly Contest 303 Q3
tags:
    - Design
    - Array
    - Hash Table
    - String
    - Ordered Set
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2353. Design a Food Rating System](https://leetcode.com/problems/design-a-food-rating-system)

[中文文档](/solution/2300-2399/2353.Design%20a%20Food%20Rating%20System/README.md)

## Description

<!-- description:start -->

<p>Design a food rating system that can do the following:</p>

<ul>
	<li><strong>Modify</strong> the rating of a food item listed in the system.</li>
	<li>Return the highest-rated food item for a type of cuisine in the system.</li>
</ul>

<p>Implement the <code>FoodRatings</code> class:</p>

<ul>
	<li><code>FoodRatings(String[] foods, String[] cuisines, int[] ratings)</code> Initializes the system. The food items are described by <code>foods</code>, <code>cuisines</code> and <code>ratings</code>, all of which have a length of <code>n</code>.

    <ul>
    	<li><code>foods[i]</code> is the name of the <code>i<sup>th</sup></code> food,</li>
    	<li><code>cuisines[i]</code> is the type of cuisine of the <code>i<sup>th</sup></code> food, and</li>
    	<li><code>ratings[i]</code> is the initial rating of the <code>i<sup>th</sup></code> food.</li>
    </ul>
    </li>
    <li><code>void changeRating(String food, int newRating)</code> Changes the rating of the food item with the name <code>food</code>.</li>
    <li><code>String highestRated(String cuisine)</code> Returns the name of the food item that has the highest rating for the given type of <code>cuisine</code>. If there is a tie, return the item with the <strong>lexicographically smaller</strong> name.</li>

</ul>

<p>Note that a string <code>x</code> is lexicographically smaller than string <code>y</code> if <code>x</code> comes before <code>y</code> in dictionary order, that is, either <code>x</code> is a prefix of <code>y</code>, or if <code>i</code> is the first position such that <code>x[i] != y[i]</code>, then <code>x[i]</code> comes before <code>y[i]</code> in alphabetic order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;FoodRatings&quot;, &quot;highestRated&quot;, &quot;highestRated&quot;, &quot;changeRating&quot;, &quot;highestRated&quot;, &quot;changeRating&quot;, &quot;highestRated&quot;]
[[[&quot;kimchi&quot;, &quot;miso&quot;, &quot;sushi&quot;, &quot;moussaka&quot;, &quot;ramen&quot;, &quot;bulgogi&quot;], [&quot;korean&quot;, &quot;japanese&quot;, &quot;japanese&quot;, &quot;greek&quot;, &quot;japanese&quot;, &quot;korean&quot;], [9, 12, 8, 15, 14, 7]], [&quot;korean&quot;], [&quot;japanese&quot;], [&quot;sushi&quot;, 16], [&quot;japanese&quot;], [&quot;ramen&quot;, 16], [&quot;japanese&quot;]]
<strong>Output</strong>
[null, &quot;kimchi&quot;, &quot;ramen&quot;, null, &quot;sushi&quot;, null, &quot;ramen&quot;]

<strong>Explanation</strong>
FoodRatings foodRatings = new FoodRatings([&quot;kimchi&quot;, &quot;miso&quot;, &quot;sushi&quot;, &quot;moussaka&quot;, &quot;ramen&quot;, &quot;bulgogi&quot;], [&quot;korean&quot;, &quot;japanese&quot;, &quot;japanese&quot;, &quot;greek&quot;, &quot;japanese&quot;, &quot;korean&quot;], [9, 12, 8, 15, 14, 7]);
foodRatings.highestRated(&quot;korean&quot;); // return &quot;kimchi&quot;
                                    // &quot;kimchi&quot; is the highest rated korean food with a rating of 9.
foodRatings.highestRated(&quot;japanese&quot;); // return &quot;ramen&quot;
                                      // &quot;ramen&quot; is the highest rated japanese food with a rating of 14.
foodRatings.changeRating(&quot;sushi&quot;, 16); // &quot;sushi&quot; now has a rating of 16.
foodRatings.highestRated(&quot;japanese&quot;); // return &quot;sushi&quot;
                                      // &quot;sushi&quot; is the highest rated japanese food with a rating of 16.
foodRatings.changeRating(&quot;ramen&quot;, 16); // &quot;ramen&quot; now has a rating of 16.
foodRatings.highestRated(&quot;japanese&quot;); // return &quot;ramen&quot;
                                      // Both &quot;sushi&quot; and &quot;ramen&quot; have a rating of 16.
                                      // However, &quot;ramen&quot; is lexicographically smaller than &quot;sushi&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>n == foods.length == cuisines.length == ratings.length</code></li>
	<li><code>1 &lt;= foods[i].length, cuisines[i].length &lt;= 10</code></li>
	<li><code>foods[i]</code>, <code>cuisines[i]</code> consist of lowercase English letters.</li>
	<li><code>1 &lt;= ratings[i] &lt;= 10<sup>8</sup></code></li>
	<li>All the strings in <code>foods</code> are <strong>distinct</strong>.</li>
	<li><code>food</code> will be the name of a food item in the system across all calls to <code>changeRating</code>.</li>
	<li><code>cuisine</code> will be a type of cuisine of <strong>at least one</strong> food item in the system across all calls to <code>highestRated</code>.</li>
	<li>At most <code>2 * 10<sup>4</sup></code> calls <strong>in total</strong> will be made to <code>changeRating</code> and <code>highestRated</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Ordered Set

We can use a hash table $\textit{d}$ to store the foods for each cuisine, where the key is the cuisine and the value is an ordered set. Each element in the ordered set is a tuple $(\textit{rating}, \textit{food})$, sorted by rating in descending order, and if the ratings are the same, sorted by food name in lexicographical order.

We can also use a hash table $\textit{g}$ to store the rating and cuisine for each food. That is, $\textit{g}[\textit{food}] = (\textit{rating}, \textit{cuisine})$.

In the constructor, we iterate through $\textit{foods}$, $\textit{cuisines}$, and $\textit{ratings}$, storing the rating and cuisine for each food in $\textit{d}$ and $\textit{g}$.

In the $\textit{changeRating}$ function, we first get the original rating $\textit{oldRating}$ and cuisine $\textit{cuisine}$ of the food $\textit{food}$, then update the rating of $\textit{g}[\textit{food}]$ to $\textit{newRating}$, remove $(\textit{oldRating}, \textit{food})$ from $\textit{d}[\textit{cuisine}]$, and add $(\textit{newRating}, \textit{food})$ to $\textit{d}[\textit{cuisine}]$.

In the $\textit{highestRated}$ function, we directly return the food name of the first element in $\textit{d}[\textit{cuisine}]$.

In terms of time complexity, the constructor has a time complexity of $O(n \log n)$, where $n$ is the number of foods. The other operations have a time complexity of $O(\log n)$. The space complexity is $O(n)$.

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
