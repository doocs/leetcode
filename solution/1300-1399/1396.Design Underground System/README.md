# [1396. 设计地铁系统](https://leetcode-cn.com/problems/design-underground-system)

[English Version](/solution/1300-1399/1396.Design%20Underground%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你实现一个类&nbsp;<code>UndergroundSystem</code>&nbsp;，它支持以下 3 种方法：</p>

<p>1.<code>&nbsp;checkIn(int id, string stationName, int t)</code></p>

<ul>
	<li>编号为&nbsp;<code>id</code>&nbsp;的乘客在 <code>t</code>&nbsp;时刻进入地铁站&nbsp;<code>stationName</code>&nbsp;。</li>
	<li>一个乘客在同一时间只能在一个地铁站进入或者离开。</li>
</ul>

<p>2.<code>&nbsp;checkOut(int id, string stationName, int t)</code></p>

<ul>
	<li>编号为&nbsp;<code>id</code>&nbsp;的乘客在 <code>t</code>&nbsp;时刻离开地铁站 <code>stationName</code>&nbsp;。</li>
</ul>

<p>3.&nbsp;<code>getAverageTime(string startStation, string endStation)</code>&nbsp;</p>

<ul>
	<li>返回从地铁站&nbsp;<code>startStation</code>&nbsp;到地铁站&nbsp;<code>endStation</code>&nbsp;的平均花费时间。</li>
	<li>平均时间计算的行程包括当前为止所有从&nbsp;<code>startStation</code>&nbsp;<strong>直接到达</strong>&nbsp;<code>endStation</code>&nbsp;的行程。</li>
	<li>调用&nbsp;<code>getAverageTime</code>&nbsp;时，询问的路线至少包含一趟行程。</li>
</ul>

<p>你可以假设所有对&nbsp;<code>checkIn</code>&nbsp;和&nbsp;<code>checkOut</code>&nbsp;的调用都是符合逻辑的。也就是说，如果一个顾客在 <strong>t<sub>1</sub></strong>&nbsp;时刻到达某个地铁站，那么他离开的时间&nbsp;<strong>t<sub>2</sub></strong>&nbsp;一定满足&nbsp;<strong>t<sub>2</sub> &gt; t<sub>1</sub></strong>&nbsp;。所有的事件都按时间顺序给出。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>
[&quot;UndergroundSystem&quot;,&quot;checkIn&quot;,&quot;checkIn&quot;,&quot;checkIn&quot;,&quot;checkOut&quot;,&quot;checkOut&quot;,&quot;checkOut&quot;,&quot;getAverageTime&quot;,&quot;getAverageTime&quot;,&quot;checkIn&quot;,&quot;getAverageTime&quot;,&quot;checkOut&quot;,&quot;getAverageTime&quot;]
[[],[45,&quot;Leyton&quot;,3],[32,&quot;Paradise&quot;,8],[27,&quot;Leyton&quot;,10],[45,&quot;Waterloo&quot;,15],[27,&quot;Waterloo&quot;,20],[32,&quot;Cambridge&quot;,22],[&quot;Paradise&quot;,&quot;Cambridge&quot;],[&quot;Leyton&quot;,&quot;Waterloo&quot;],[10,&quot;Leyton&quot;,24],[&quot;Leyton&quot;,&quot;Waterloo&quot;],[10,&quot;Waterloo&quot;,38],[&quot;Leyton&quot;,&quot;Waterloo&quot;]]

<strong>输出：</strong>
[null,null,null,null,null,null,null,14.0,11.0,null,11.0,null,12.0]

<strong>解释：</strong>
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(45, &quot;Leyton&quot;, 3);
undergroundSystem.checkIn(32, &quot;Paradise&quot;, 8);
undergroundSystem.checkIn(27, &quot;Leyton&quot;, 10);
undergroundSystem.checkOut(45, &quot;Waterloo&quot;, 15);
undergroundSystem.checkOut(27, &quot;Waterloo&quot;, 20);
undergroundSystem.checkOut(32, &quot;Cambridge&quot;, 22);
undergroundSystem.getAverageTime(&quot;Paradise&quot;, &quot;Cambridge&quot;);       // 返回 14.0。从 &quot;Paradise&quot;（时刻 8）到 &quot;Cambridge&quot;(时刻 22)的行程只有一趟
undergroundSystem.getAverageTime(&quot;Leyton&quot;, &quot;Waterloo&quot;);          // 返回 11.0。总共有 2 躺从 &quot;Leyton&quot; 到 &quot;Waterloo&quot; 的行程，编号为 id=45 的乘客出发于 time=3 到达于 time=15，编号为 id=27 的乘客于 time=10 出发于 time=20 到达。所以平均时间为 ( (15-3) + (20-10) ) / 2 = 11.0
undergroundSystem.checkIn(10, &quot;Leyton&quot;, 24);
undergroundSystem.getAverageTime(&quot;Leyton&quot;, &quot;Waterloo&quot;);          // 返回 11.0
undergroundSystem.checkOut(10, &quot;Waterloo&quot;, 38);
undergroundSystem.getAverageTime(&quot;Leyton&quot;, &quot;Waterloo&quot;);          // 返回 12.0</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>总共最多有&nbsp;<code>20000</code>&nbsp;次操作。</li>
	<li><code>1 &lt;= id, t &lt;= 10^6</code></li>
	<li>所有的字符串包含大写字母，小写字母和数字。</li>
	<li><code>1 &lt;=&nbsp;stationName.length &lt;= 10</code></li>
	<li>与标准答案误差在&nbsp;<code>10^-5</code>&nbsp;以内的结果都视为正确结果。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“哈希表”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class UndergroundSystem:

    def __init__(self):
        self.check_in_station = {}
        self.check_in_time = {}
        self.total_time = {}

    def checkIn(self, id: int, stationName: str, t: int) -> None:
        self.check_in_station[id] = stationName
        self.check_in_time[id] = t

    def checkOut(self, id: int, stationName: str, t: int) -> None:
        cost = t - self.check_in_time.pop(id)
        start_station = self.check_in_station.pop(id)
        stations = start_station + '.' + stationName
        times = self.total_time.get(stations, [0, 0])
        times[0] += cost
        times[1] += 1
        self.total_time[stations] = times

    def getAverageTime(self, startStation: str, endStation: str) -> float:
        stations = startStation + '.' + endStation
        times = self.total_time[stations]
        return times[0] / times[1]


# Your UndergroundSystem object will be instantiated and called as such:
# obj = UndergroundSystem()
# obj.checkIn(id,stationName,t)
# obj.checkOut(id,stationName,t)
# param_3 = obj.getAverageTime(startStation,endStation)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class UndergroundSystem {
    private Map<Integer, String> checkInStation;
    private Map<Integer, Integer> checkInTime;
    private Map<String, int[]> totalTime;

    public UndergroundSystem() {
        checkInStation = new HashMap<>();
        checkInTime = new HashMap<>();
        totalTime = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInStation.put(id, stationName);
        checkInTime.put(id, t);
    }

    public void checkOut(int id, String stationName, int t) {
        int cost = t - checkInTime.remove(id);
        String startStation = checkInStation.remove(id);
        String stations = startStation + "." + stationName;
        int[] times = totalTime.getOrDefault(stations, new int[2]);
        times[0] += cost;
        ++times[1];
        totalTime.put(stations, times);
    }

    public double getAverageTime(String startStation, String endStation) {
        String stations = startStation + "." + endStation;
        int[] times = totalTime.get(stations);
        return times[0] * 1.0 / times[1];
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
```

### **...**

```

```

<!-- tabs:end -->
