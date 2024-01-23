# [3015. Count the Number of Houses at a Certain Distance I](https://leetcode.com/problems/count-the-number-of-houses-at-a-certain-distance-i)

[中文文档](/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/README.md)

## Description

<p>You are given three <strong>positive</strong> integers <code>n</code>, <code>x</code>, and <code>y</code>.</p>

<p>In a city, there exist houses numbered <code>1</code> to <code>n</code> connected by <code>n</code> streets. There is a street connecting the house numbered <code>i</code> with the house numbered <code>i + 1</code> for all <code>1 &lt;= i &lt;= n - 1</code> . An additional street connects the house numbered <code>x</code> with the house numbered <code>y</code>.</p>

<p>For each <code>k</code>, such that <code>1 &lt;= k &lt;= n</code>, you need to find the number of <strong>pairs of houses</strong> <code>(house<sub>1</sub>, house<sub>2</sub>)</code> such that the <strong>minimum</strong> number of streets that need to be traveled to reach <code>house<sub>2</sub></code> from <code>house<sub>1</sub></code> is <code>k</code>.</p>

<p>Return <em>a <strong>1-indexed</strong> array </em><code>result</code><em> of length </em><code>n</code><em> where </em><code>result[k]</code><em> represents the <strong>total</strong> number of pairs of houses such that the <strong>minimum</strong> streets required to reach one house from the other is </em><code>k</code>.</p>

<p><strong>Note</strong> that <code>x</code> and <code>y</code> can be <strong>equal</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/images/example2.png" style="width: 474px; height: 197px;" />
<pre>
<strong>Input:</strong> n = 3, x = 1, y = 3
<strong>Output:</strong> [6,0,0]
<strong>Explanation:</strong> Let&#39;s look at each pair of houses:
- For the pair (1, 2), we can go from house 1 to house 2 directly.
- For the pair (2, 1), we can go from house 2 to house 1 directly.
- For the pair (1, 3), we can go from house 1 to house 3 directly.
- For the pair (3, 1), we can go from house 3 to house 1 directly.
- For the pair (2, 3), we can go from house 2 to house 3 directly.
- For the pair (3, 2), we can go from house 3 to house 2 directly.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/images/example3.png" style="width: 668px; height: 174px;" />
<pre>
<strong>Input:</strong> n = 5, x = 2, y = 4
<strong>Output:</strong> [10,8,2,0,0]
<strong>Explanation:</strong> For each distance k the pairs are:
- For k == 1, the pairs are (1, 2), (2, 1), (2, 3), (3, 2), (2, 4), (4, 2), (3, 4), (4, 3), (4, 5), and (5, 4).
- For k == 2, the pairs are (1, 3), (3, 1), (1, 4), (4, 1), (2, 5), (5, 2), (3, 5), and (5, 3).
- For k == 3, the pairs are (1, 5), and (5, 1).
- For k == 4 and k == 5, there are no pairs.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3015.Count%20the%20Number%20of%20Houses%20at%20a%20Certain%20Distance%20I/images/example5.png" style="width: 544px; height: 130px;" />
<pre>
<strong>Input:</strong> n = 4, x = 1, y = 1
<strong>Output:</strong> [6,4,2,0]
<strong>Explanation:</strong> For each distance k the pairs are:
- For k == 1, the pairs are (1, 2), (2, 1), (2, 3), (3, 2), (3, 4), and (4, 3).
- For k == 2, the pairs are (1, 3), (3, 1), (2, 4), and (4, 2).
- For k == 3, the pairs are (1, 4), and (4, 1).
- For k == 4, there are no pairs.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= x, y &lt;= n</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def countOfPairs(self, n: int, x: int, y: int) -> List[int]:
        adj = collections.defaultdict( list )
        for i in range( 1 , n ):
            adj[i-1].append( i )
            adj[i].append( i-1 )
        if x != y :
            x-=1 
            y-=1 
            adj[x].append( y )
            adj[y].append( x )
        ans = [ 0 ]*n
        def bfs( start ):
            arr = [ inf ]*n
            q =  [ ( 0 , start ) ] 
            heapq.heapify( q )
            while q : 
                size = len( q )
                while size :
                    size -=1 
                    dist , node = heapq.heappop( q )
                    if arr[ node ] <= dist : continue 
                    arr[node] = dist 
                    for child in adj[ node ] : 
                        heapq.heappush( q , ( dist+1 , child ))
            for i in arr :
                if i != 0 and i!= inf :
                    ans[i-1] +=1

        for i in range(  n ):
            bfs( i )
        
        return ans
```

```java
class Solution {
    public int[] countOfPairs(int n, int x, int y) {
        int[][] grid = new int[n + 1][n + 1];

        for (int[] row : grid) {
            Arrays.fill(row, 100000);
        }

        for (int j = 1; j < n; j++) {
            grid[j][j + 1] = 1;
            grid[j + 1][j] = 1;
        }
        grid[x][y] = 1;
        grid[y][x] = 1;

        for (int via = 1; via <= n; via++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    grid[i][j] = Math.min(grid[i][j], grid[i][via] + grid[via][j]);
                }
            }
        }

        int[] result = new int[n];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    int val = grid[i][j];
                    result[val - 1]++;
                }
            }
        }

        return result;
    }
}
```

```cpp
class Solution {
public:
    vector<int> countOfPairs(int n, int x, int y) {
        vector<int> result(n);
        for(int i = 0; i < n; i++){
            result[i] = (n - i - 1) * 2;
        }
        if(x > y) swap(x, y);
        if((y-x) <= 1){
            return result;
        }
        int end = (x+y) / 2 - 1;
        int start = (x+y)/2 + 1;
        for(int i = 1; i <= end; i++){
            for(int j = start; j <= n; j++){
                int dist_old = j - i;
                int dist_new = abs(x - i) + 1 + abs(y - j);
                if(dist_old > dist_new){
                    result[dist_new-1] += 2;
                    result[dist_old-1] -= 2;
                }
            }
        }
        return result;
    }
};
```

```go
func countOfPairs(n int, x int, y int) []int {
    if x > y { x,y = y,x }
    res := make([]int, n)    
    for i := 1; i <= n; i++ {
        for j := i+1; j <= n; j++ {
            v := min(j-i, 1+abs(i-x)+abs(j-y))
            res[v-1] += 2
        }
    }
    return res
}
func min(a,b int) int {
    if a < b { return a }
    return b
}
func abs(a int) int {
    if a < 0 { return -a }
    return a
}
```

<!-- tabs:end -->

<!-- end -->
