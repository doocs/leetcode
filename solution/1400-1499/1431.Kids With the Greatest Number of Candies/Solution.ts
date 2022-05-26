var kidsWithCandies = function (candies: number[], extraCandies: number): boolean[] {
  let maxCandies = Math.max(...candies);
  return candies.map(candy => candy + extraCandies >= maxCandies);
};