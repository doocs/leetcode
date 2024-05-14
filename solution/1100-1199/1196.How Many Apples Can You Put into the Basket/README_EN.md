# [1196. How Many Apples Can You Put into the Basket 🔒](https://leetcode.com/problems/how-many-apples-can-you-put-into-the-basket)

[中文文档](/solution/1100-1199/1196.How%20Many%20Apples%20Can%20You%20Put%20into%20the%20Basket/README.md)

<!-- tags:Greedy,Array,Sorting -->

<!-- difficulty:Easy -->

## Description

<p>You have some apples and a basket that can carry up to <code>5000</code> units of weight.</p>

<p>Given an integer array <code>weight</code> where <code>weight[i]</code> is the weight of the <code>i<sup>th</sup></code> apple, return <em>the maximum number of apples you can put in the basket</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> weight = [100,200,150,1000]
<strong>Output:</strong> 4
<strong>Explanation:</strong> All 4 apples can be carried by the basket since their sum of weights is 1450.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> weight = [900,950,800,1000,700,800]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The sum of weights of the 6 apples exceeds 5000 so we choose any 5 of them.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= weight.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= weight[i] &lt;= 10<sup>3</sup></code></li>
</ul>

## Solutions

### Solution 1: Greedy Algorithm

To maximize the number of apples, we should try to minimize the weight of the apples. Therefore, we can sort the weights of the apples, and then put them into the basket in ascending order until the weight of the basket exceeds $5000$. We then return the number of apples in the basket at this point.

If all the apples can be put into the basket, then we return the total number of apples.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the number of apples.

<!-- tabs:start -->

```python
class Solution:
    def maxNumberOfApples(self, weight: List[int]) -> int:
        weight.sort()
        s = 0
        for i, x in enumerate(weight):
            s += x
            if s > 5000:
                return i
        return len(weight)
```

```java
class Solution {
    public int maxNumberOfApples(int[] weight) {
        Arrays.sort(weight);
        int s = 0;
        for (int i = 0; i < weight.length; ++i) {
            s += weight[i];
            if (s > 5000) {
                return i;
            }
        }
        return weight.length;
    }
}
```

```cpp
class Solution {
public:
    int maxNumberOfApples(vector<int>& weight) {
        sort(weight.begin(), weight.end());
        int s = 0;
        for (int i = 0; i < weight.size(); ++i) {
            s += weight[i];
            if (s > 5000) {
                return i;
            }
        }
        return weight.size();
    }
};
```

```go
func maxNumberOfApples(weight []int) int {
	sort.Ints(weight)
	s := 0
	for i, x := range weight {
		s += x
		if s > 5000 {
			return i
		}
	}
	return len(weight)
}
```

```ts
function maxNumberOfApples(weight: number[]): number {
    weight.sort((a, b) => a - b);
    let s = 0;
    for (let i = 0; i < weight.length; ++i) {
        s += weight[i];
        if (s > 5000) {
            return i;
        }
    }
    return weight.length;
}
```

<!-- tabs:end -->

<!-- end -->
