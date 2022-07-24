from sortedcontainers import SortedSet


class FoodRatings:
    def __init__(self, foods: List[str], cuisines: List[str], ratings: List[int]):
        self.mp = {}
        self.t = defaultdict(lambda: SortedSet(key=lambda x: (-x[0], x[1])))

        for a, b, c in zip(foods, cuisines, ratings):
            self.mp[a] = (b, c)
            self.t[b].add((c, a))

    def changeRating(self, food: str, newRating: int) -> None:
        b, c = self.mp[food]
        self.mp[food] = (b, newRating)
        self.t[b].remove((c, food))
        self.t[b].add((newRating, food))

    def highestRated(self, cuisine: str) -> str:
        return self.t[cuisine][0][1]


# Your FoodRatings object will be instantiated and called as such:
# obj = FoodRatings(foods, cuisines, ratings)
# obj.changeRating(food,newRating)
# param_2 = obj.highestRated(cuisine)
