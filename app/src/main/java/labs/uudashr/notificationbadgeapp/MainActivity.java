package labs.uudashr.notificationbadgeapp;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private int mNotificationCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.plus_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusOneClick();
            }
        });

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetClick();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_notifications);
        setupBadge(menuItem);

        return true;
    }


    private void setupBadge(final MenuItem menuItem) {
        View actionView = MenuItemCompat.getActionView(menuItem);
        TextView notificationBadge = (TextView) actionView.findViewById(R.id.notification_badge);

        if (mNotificationCount == 0) {
            if (notificationBadge.getVisibility() != View.GONE) {
                notificationBadge.setVisibility(View.GONE);
            }
        } else {
            notificationBadge.setText(String.valueOf(Math.min(mNotificationCount, 99)));
            if (notificationBadge.getVisibility() != View.VISIBLE) {
                notificationBadge.setVisibility(View.VISIBLE);
            }
        }

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notifications:
                Toast.makeText(this, "Notifications click", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void plusOneClick() {
        mNotificationCount++;
        supportInvalidateOptionsMenu();
    }

    private void resetClick() {
        mNotificationCount = 0;
        supportInvalidateOptionsMenu();
    }
}
