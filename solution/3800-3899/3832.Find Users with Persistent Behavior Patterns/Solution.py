import pandas as pd


def find_behaviorally_stable_users(activity: pd.DataFrame) -> pd.DataFrame:
    activity['action_date'] = pd.to_datetime(activity['action_date'])

    # Filter users with only a single action per day
    df = activity.assign(
        cnt=activity.groupby(['user_id', 'action_date'])['action'].transform('count')
    )
    df = df[df['cnt'] == 1].sort_values(['user_id', 'action', 'action_date'])

    # Identify consecutive intervals
    df['rn'] = df.groupby(['user_id', 'action'])['action_date'].rank(method='first')
    df['grp'] = df['action_date'] - pd.to_timedelta(df['rn'], unit='D')

    # Aggregate streaks
    streaks = (
        df.groupby(['user_id', 'action', 'grp'])
        .agg(
            streak_length=('action_date', 'count'),
            start_date=('action_date', 'min'),
            end_date=('action_date', 'max'),
        )
        .reset_index()
    )

    # Filter and get the longest streak for each user
    res = streaks[streaks['streak_length'] >= 5].sort_values(
        ['streak_length', 'user_id'], ascending=[False, True]
    )

    return res.groupby('user_id').head(1)[
        ['user_id', 'action', 'streak_length', 'start_date', 'end_date']
    ]
