function filterRestaurants(
    restaurants: number[][],
    veganFriendly: number,
    maxPrice: number,
    maxDistance: number,
): number[] {
    restaurants.sort((a, b) => (a[1] === b[1] ? b[0] - a[0] : b[1] - a[1]));
    const ans: number[] = [];
    for (const [id, _, vegan, price, distance] of restaurants) {
        if (vegan >= veganFriendly && price <= maxPrice && distance <= maxDistance) {
            ans.push(id);
        }
    }
    return ans;
}
