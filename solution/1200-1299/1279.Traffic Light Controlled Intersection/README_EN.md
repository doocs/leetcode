# [1279. Traffic Light Controlled Intersection](https://leetcode.com/problems/traffic-light-controlled-intersection)

[中文文档](/solution/1200-1299/1279.Traffic%20Light%20Controlled%20Intersection/README.md)

## Description

<p>There is an intersection of two roads. First road is road A where cars travel&nbsp;from North to South in direction 1 and from South to North in direction 2. Second road is road B where cars travel from West to East in direction 3 and from East to West in direction 4.</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1279.Traffic%20Light%20Controlled%20Intersection/images/exp.png" style="width: 600px; height: 417px;" /></p>

<p>There is a traffic light&nbsp;located on each road before the intersection. A traffic light&nbsp;can&nbsp;either be green or red.</p>

<ol>
	<li><strong>Green</strong> means&nbsp;cars can cross the intersection&nbsp;in both directions of the road.</li>
	<li><strong>Red</strong> means cars in both directions cannot cross the intersection and must wait until the light turns green.</li>
</ol>

<p>The traffic lights cannot be green on both roads at the same time. That means when the light is green on road A, it is red on&nbsp;road B and when the light is green on road B, it is red on&nbsp;road A.</p>

<p>Initially, the traffic light is <strong>green</strong> on road A and <strong>red</strong>&nbsp;on road B. When the light is green on one road, all cars can cross the intersection in both directions until the light becomes green on the other road.&nbsp;No two cars traveling on different roads should cross at the same time.</p>

<p>Design a deadlock-free&nbsp;traffic light controlled system at this intersection.</p>

<p>Implement the function&nbsp;<code>void carArrived(carId, roadId, direction, turnGreen, crossCar)</code> where:</p>

<ul>
	<li><code>carId</code>&nbsp;is the id of the car that arrived.</li>
	<li><code>roadId</code>&nbsp;is the id of the road that the car travels&nbsp;on.</li>
	<li><code>direction</code>&nbsp;is the direction of the car.</li>
	<li><code>turnGreen</code>&nbsp;is a function you can call to turn the traffic light to green on the current road.</li>
	<li><code>crossCar</code>&nbsp;is a function you can call to let the current car cross the intersection.</li>
</ul>

<p>Your answer is considered correct if it avoids cars deadlock in the intersection.&nbsp;Turning the light green on a road when it was already green is considered a&nbsp;wrong answer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> cars = [1,3,5,2,4], directions = [2,1,2,4,3], arrivalTimes = [10,20,30,40,50]
<strong>Output:</strong> [
&quot;Car 1 Has Passed Road A In Direction 2&quot;,    // Traffic light on road A is green, car 1 can cross the intersection.
&quot;Car 3 Has Passed Road A In Direction 1&quot;,    // Car 3 crosses the intersection as the light is still green.
&quot;Car 5 Has Passed Road A In Direction 2&quot;,    // Car 5 crosses the intersection as the light is still green.
&quot;Traffic Light On Road B Is Green&quot;,          // Car 2 requests green light for road B.
&quot;Car 2 Has Passed Road B In Direction 4&quot;,    // Car 2 crosses as the light is green on road B now.
&quot;Car 4 Has Passed Road B In Direction 3&quot;     // Car 4 crosses the intersection as the light is still green.
]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> cars = [1,2,3,4,5], directions = [2,4,3,3,1], arrivalTimes = [10,20,30,40,40]
<strong>Output:</strong> [
&quot;Car 1 Has Passed Road A In Direction 2&quot;,    // Traffic light on road A is green, car 1 can cross the intersection.
&quot;Traffic Light On Road B Is Green&quot;,          // Car 2 requests green light for road B.
&quot;Car 2 Has Passed Road B In Direction 4&quot;,    // Car 2 crosses as the light is green on road B now.
&quot;Car 3 Has Passed Road B In Direction 3&quot;,    // Car 3 crosses as the light is green on road B now.
&quot;Traffic Light On Road A Is Green&quot;,          // Car 5 requests green light for road A.
&quot;Car 5 Has Passed Road A In Direction 1&quot;,    // Car 5 crosses as the light is green on road A now.
&quot;Traffic Light On Road B Is Green&quot;,          // Car 4 requests green light for road B. Car 4 blocked until car 5 crosses and then traffic light is green on road B.
&quot;Car 4 Has Passed Road B In Direction 3&quot;     // Car 4 crosses as the light is green on road B now.
]
<strong>Explanation:</strong> This is a dead-lock free scenario. Note that the scenario when car 4 crosses before turning light into green on road A and allowing car 5 to pass is also <strong>correct</strong> and <strong>Accepted</strong> scenario.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= cars.length &lt;= 20</code></li>
	<li><code>cars.length = directions.length</code></li>
	<li><code>cars.length = arrivalTimes.length</code></li>
	<li>All values of <code>cars</code> are unique</li>
	<li><code>1 &lt;= directions[i] &lt;= 4</code></li>
	<li><code>arrivalTimes</code> is non-decreasing</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
from threading import Lock


class TrafficLight:
    def __init__(self):
        self.lock = Lock()
        self.road = 1

    def carArrived(
        self,
        carId: int,                      # ID of the car
        # ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
        roadId: int,
        direction: int,                  # Direction of the car
        # Use turnGreen() to turn light to green on current road
        turnGreen: 'Callable[[], None]',
        # Use crossCar() to make car cross the intersection
        crossCar: 'Callable[[], None]'
    ) -> None:
        self.lock.acquire()
        if self.road != roadId:
            self.road = roadId
            turnGreen()
        crossCar()
        self.lock.release()
```

### **Java**

```java
class TrafficLight {
    private int road = 1;

    public TrafficLight() {
    }

    public synchronized void carArrived(int carId, // ID of the car
        int roadId, // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
        int direction, // Direction of the car
        Runnable turnGreen, // Use turnGreen.run() to turn light to green on current road
        Runnable crossCar // Use crossCar.run() to make car cross the intersection
    ) {
        if (roadId != road) {
            turnGreen.run();
            road = roadId;
        }
        crossCar.run();
    }
}
```

### **...**

```

```

<!-- tabs:end -->
