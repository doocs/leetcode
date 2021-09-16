class Solution
{
public:
    vector<int> rightSideView ( TreeNode* root )
    {
        vector<int> res;

        if ( !root )
        {
            return res;
        }

        queue<TreeNode* > que;
        que.push ( root );

        while ( !que.empty() )
        {
            int size = que.size();

            for ( int i = 0; i < size; i++ )
            {
                TreeNode* ptr = que.front();
                que.pop();

                if ( i == size - 1 )
                {
                    res.push_back ( ptr->val );
                }

                if ( ptr->left )
                {
                    que.push ( ptr->left );
                }

                if ( ptr-> right )
                {
                    que.push ( ptr->right );
                }
            }
        }

        return res;
    }
};
