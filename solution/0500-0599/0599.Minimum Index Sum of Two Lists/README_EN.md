# [599. Minimum Index Sum of Two Lists](https://leetcode.com/problems/minimum-index-sum-of-two-lists)

[中文文档](/solution/0500-0599/0599.Minimum%20Index%20Sum%20of%20Two%20Lists/README.md)

## Description

<p>Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.</p>

<p>You need to help them find out their <b>common interest</b> with the <b>least list index sum</b>. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> list1 = [&quot;Shogun&quot;,&quot;Tapioca Express&quot;,&quot;Burger King&quot;,&quot;KFC&quot;], list2 = [&quot;Piatti&quot;,&quot;The Grill at Torrey Pines&quot;,&quot;Hungry Hunter Steakhouse&quot;,&quot;Shogun&quot;]
<strong>Output:</strong> [&quot;Shogun&quot;]
<strong>Explanation:</strong> The only restaurant they both like is &quot;Shogun&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> list1 = [&quot;Shogun&quot;,&quot;Tapioca Express&quot;,&quot;Burger King&quot;,&quot;KFC&quot;], list2 = [&quot;KFC&quot;,&quot;Shogun&quot;,&quot;Burger King&quot;]
<strong>Output:</strong> [&quot;Shogun&quot;]
<strong>Explanation:</strong> The restaurant they both like and have the least index sum is &quot;Shogun&quot; with index sum 1 (0+1).
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> list1 = [&quot;Shogun&quot;,&quot;Tapioca Express&quot;,&quot;Burger King&quot;,&quot;KFC&quot;], list2 = [&quot;KFC&quot;,&quot;Burger King&quot;,&quot;Tapioca Express&quot;,&quot;Shogun&quot;]
<strong>Output:</strong> [&quot;KFC&quot;,&quot;Burger King&quot;,&quot;Tapioca Express&quot;,&quot;Shogun&quot;]
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> list1 = [&quot;Shogun&quot;,&quot;Tapioca Express&quot;,&quot;Burger King&quot;,&quot;KFC&quot;], list2 = [&quot;KNN&quot;,&quot;KFC&quot;,&quot;Burger King&quot;,&quot;Tapioca Express&quot;,&quot;Shogun&quot;]
<strong>Output:</strong> [&quot;KFC&quot;,&quot;Burger King&quot;,&quot;Tapioca Express&quot;,&quot;Shogun&quot;]
</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> list1 = [&quot;KFC&quot;], list2 = [&quot;KFC&quot;]
<strong>Output:</strong> [&quot;KFC&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= list1.length, list2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= list1[i].length, list2[i].length &lt;= 30</code></li>
	<li><code>list1[i]</code> and <code>list2[i]</code> consist of spaces <code>&#39; &#39;</code> and English letters.</li>
	<li>All the stings of <code>list1</code> are <strong>unique</strong>.</li>
	<li>All the stings of <code>list2</code>&nbsp;are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findRestaurant(self, list1: List[str], list2: List[str]) -> List[str]:
        ans = []
        mp = {v: i for i, v in enumerate(list2)}
        mi = 2000
        for i, v in enumerate(list1):
            if v in mp:
                t = i + mp[v]
                if t < mi:
                    mi = t
                    ans = [v]
                elif t == mi:
                    ans.append(v)
        return ans
```

### **Java**

```java
class Solution {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> mp = new HashMap<>();
        for (int i = 0; i < list2.length; ++i) {
            mp.put(list2[i], i);
        }
        List<String> ans = new ArrayList<>();
        int mi = 2000;
        for (int i = 0; i < list1.length; ++i) {
            if (mp.containsKey(list1[i])) {
                int t = i + mp.get(list1[i]);
                if (t < mi) {
                    ans = new ArrayList<>();
                    ans.add(list1[i]);
                    mi = t;
                } else if (t == mi) {
                    ans.add(list1[i]);
                }
            }
        }
        return ans.toArray(new String[0]);
    }
}

```

### **C++**

```cpp
class Solution {
public:
    vector<string> findRestaurant(vector<string>& list1, vector<string>& list2) {
        unordered_map<string, int> mp;
        for (int i = 0; i < list2.size(); ++i) mp[list2[i]] = i;
        int mi = 2000;
        vector<string> ans;
        for (int i = 0; i < list1.size(); ++i)
        {
            if (mp.count(list1[i]))
            {
                int t = i + mp[list1[i]];
                if (t < mi)
                {
                    ans.clear();
                    ans.push_back(list1[i]);
                    mi = t;
                }
                else if (t == mi)
                {
                    ans.push_back(list1[i]);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```cpp
func findRestaurant(list1 []string, list2 []string) []string {
	mp := make(map[string]int)
	for i, v := range list2 {
		mp[v] = i
	}
	mi := 2000
	var ans []string
	for i, v := range list1 {
		if _, ok := mp[v]; ok {
			t := i + mp[v]
			if t < mi {
				ans = []string{v}
				mi = t
			} else if t == mi {
				ans = append(ans, v)
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
