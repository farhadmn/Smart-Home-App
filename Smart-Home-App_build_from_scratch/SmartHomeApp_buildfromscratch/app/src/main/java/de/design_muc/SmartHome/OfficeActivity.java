package de.design_muc.SmartHome;

public class OfficeActivity extends BaseActivity {


    @Override
    int getContentViewId() {
        return R.layout.activity_work;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_notifications;
    }
}
