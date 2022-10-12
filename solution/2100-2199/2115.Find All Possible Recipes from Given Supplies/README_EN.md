# [2115. Find All Possible Recipes from Given Supplies](https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies)

[中文文档](/solution/2100-2199/2115.Find%20All%20Possible%20Recipes%20from%20Given%20Supplies/README.md)

## Description

<p>You have information about <code>n</code> different recipes. You are given a string array <code>recipes</code> and a 2D string array <code>ingredients</code>. The <code>i<sup>th</sup></code> recipe has the name <code>recipes[i]</code>, and you can <strong>create</strong> it if you have <strong>all</strong> the needed ingredients from <code>ingredients[i]</code>. Ingredients to a recipe may need to be created from <strong>other </strong>recipes, i.e., <code>ingredients[i]</code> may contain a string that is in <code>recipes</code>.</p>

<p>You are also given a string array <code>supplies</code> containing all the ingredients that you initially have, and you have an infinite supply of all of them.</p>

<p>Return <em>a list of all the recipes that you can create. </em>You may return the answer in <strong>any order</strong>.</p>

<p>Note that two recipes may contain each other in their ingredients.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> recipes = [&quot;bread&quot;], ingredients = [[&quot;yeast&quot;,&quot;flour&quot;]], supplies = [&quot;yeast&quot;,&quot;flour&quot;,&quot;corn&quot;]
<strong>Output:</strong> [&quot;bread&quot;]
<strong>Explanation:</strong>
We can create &quot;bread&quot; since we have the ingredients &quot;yeast&quot; and &quot;flour&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> recipes = [&quot;bread&quot;,&quot;sandwich&quot;], ingredients = [[&quot;yeast&quot;,&quot;flour&quot;],[&quot;bread&quot;,&quot;meat&quot;]], supplies = [&quot;yeast&quot;,&quot;flour&quot;,&quot;meat&quot;]
<strong>Output:</strong> [&quot;bread&quot;,&quot;sandwich&quot;]
<strong>Explanation:</strong>
We can create &quot;bread&quot; since we have the ingredients &quot;yeast&quot; and &quot;flour&quot;.
We can create &quot;sandwich&quot; since we have the ingredient &quot;meat&quot; and can create the ingredient &quot;bread&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> recipes = [&quot;bread&quot;,&quot;sandwich&quot;,&quot;burger&quot;], ingredients = [[&quot;yeast&quot;,&quot;flour&quot;],[&quot;bread&quot;,&quot;meat&quot;],[&quot;sandwich&quot;,&quot;meat&quot;,&quot;bread&quot;]], supplies = [&quot;yeast&quot;,&quot;flour&quot;,&quot;meat&quot;]
<strong>Output:</strong> [&quot;bread&quot;,&quot;sandwich&quot;,&quot;burger&quot;]
<strong>Explanation:</strong>
We can create &quot;bread&quot; since we have the ingredients &quot;yeast&quot; and &quot;flour&quot;.
We can create &quot;sandwich&quot; since we have the ingredient &quot;meat&quot; and can create the ingredient &quot;bread&quot;.
We can create &quot;burger&quot; since we have the ingredient &quot;meat&quot; and can create the ingredients &quot;bread&quot; and &quot;sandwich&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == recipes.length == ingredients.length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= ingredients[i].length, supplies.length &lt;= 100</code></li>
	<li><code>1 &lt;= recipes[i].length, ingredients[i][j].length, supplies[k].length &lt;= 10</code></li>
	<li><code>recipes[i], ingredients[i][j]</code>, and <code>supplies[k]</code> consist only of lowercase English letters.</li>
	<li>All the values of <code>recipes</code> and <code>supplies</code>&nbsp;combined are unique.</li>
	<li>Each <code>ingredients[i]</code> does not contain any duplicate values.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findAllRecipes(self, recipes: List[str], ingredients: List[List[str]], supplies: List[str]) -> List[str]:
        g = defaultdict(list)
        indeg = defaultdict(int)
        for a, b in zip(recipes, ingredients):
            for v in b:
                g[v].append(a)
            indeg[a] += len(b)
        q = deque(supplies)
        ans = []
        while q:
            for _ in range(len(q)):
                i = q.popleft()
                for j in g[i]:
                    indeg[j] -= 1
                    if indeg[j] == 0:
                        ans.append(j)
                        q.append(j)
        return ans
```

### **Java**

```java
class Solution {
    public List<String> findAllRecipes(
        String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> g = new HashMap<>();
        Map<String, Integer> indeg = new HashMap<>();
        for (int i = 0; i < recipes.length; ++i) {
            for (String v : ingredients.get(i)) {
                g.computeIfAbsent(v, k -> new ArrayList<>()).add(recipes[i]);
            }
            indeg.put(recipes[i], ingredients.get(i).size());
        }
        Deque<String> q = new ArrayDeque<>();
        for (String s : supplies) {
            q.offer(s);
        }
        List<String> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            for (int n = q.size(); n > 0; --n) {
                String i = q.pollFirst();
                for (String j : g.getOrDefault(i, Collections.emptyList())) {
                    indeg.put(j, indeg.get(j) - 1);
                    if (indeg.get(j) == 0) {
                        ans.add(j);
                        q.offer(j);
                    }
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findAllRecipes(vector<string>& recipes, vector<vector<string>>& ingredients, vector<string>& supplies) {
        unordered_map<string, vector<string>> g;
        unordered_map<string, int> indeg;
        for (int i = 0; i < recipes.size(); ++i) {
            for (auto& v : ingredients[i]) {
                g[v].push_back(recipes[i]);
            }
            indeg[recipes[i]] = ingredients[i].size();
        }
        queue<string> q;
        for (auto& s : supplies) {
            q.push(s);
        }
        vector<string> ans;
        while (!q.empty()) {
            for (int n = q.size(); n; --n) {
                auto i = q.front();
                q.pop();
                for (auto j : g[i]) {
                    if (--indeg[j] == 0) {
                        ans.push_back(j);
                        q.push(j);
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func findAllRecipes(recipes []string, ingredients [][]string, supplies []string) []string {
	g := map[string][]string{}
	indeg := map[string]int{}
	for i, a := range recipes {
		for _, b := range ingredients[i] {
			g[b] = append(g[b], a)
		}
		indeg[a] = len(ingredients[i])
	}
	q := []string{}
	for _, s := range supplies {
		q = append(q, s)
	}
	ans := []string{}
	for len(q) > 0 {
		for n := len(q); n > 0; n-- {
			i := q[0]
			q = q[1:]
			for _, j := range g[i] {
				indeg[j]--
				if indeg[j] == 0 {
					ans = append(ans, j)
					q = append(q, j)
				}
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
