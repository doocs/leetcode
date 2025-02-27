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
