package net.iyouyu.schedule;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ScheduleAppWidgetProvider extends AppWidgetProvider {
	private static final String UPDATE_ACTION = "net.iyouyu.schedule.UPDATE_APP_WIDGET";
	Link link = new Link();

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		System.out.println("onUpdate");
		link.doWork();
		String a = link.doSelect();
		link.doClose();
		String[] b = a.split("` ~");
		System.out.println(a);
		
		for (int i = 0; i < appWidgetIds.length; i++) {
			Intent intent = new Intent();
			// ÎªIntent¶ÔÏóÉèÖÃAction
			intent.setAction(UPDATE_ACTION);
			// Ê¹ÓÃgetBroadcast·œ·š£¬µÃµœÒ»žöPendingIntent¶ÔÏó£¬µ±žÃ¶ÔÏóÖŽÐÐÊ±£¬»á·¢ËÍÒ»žö¹ã²¥
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
					0, intent, 0);
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.schedule_appwidget);
			remoteViews.setOnClickPendingIntent(R.id.but, pendingIntent);
			// remoteViews.setOnClickPendingIntent(R.id.widgetText2,
			// pendingIntent);
			// remoteViews.setOnClickPendingIntent(R.id.widgetText3,
			// pendingIntent);
			// remoteViews.setOnClickPendingIntent(R.id.widgetText4,
			// pendingIntent);
			// remoteViews.setOnClickPendingIntent(R.id.widgetText5,
			// pendingIntent);
			// remoteViews.setOnClickPendingIntent(R.id.widgetText6,
			// pendingIntent);
			// remoteViews.setOnClickPendingIntent(R.id.widgetText7,
			// pendingIntent);

			remoteViews.setTextViewText(R.id.widgetText2, "   1-2:" + b[1]);
			remoteViews.setTextViewText(R.id.widgetText3, "   3-4:" + b[2]);
			remoteViews.setTextViewText(R.id.widgetText4, "   5-6:" + b[3]);
			remoteViews.setTextViewText(R.id.widgetText5, "   7-8:" + b[4]);
			remoteViews.setTextViewText(R.id.widgetText6, "   9-10:" + b[5]);
			remoteViews.setTextViewText(R.id.widgetText7, "   11-12:" + b[6]);
		
			// remoteViews.setTextColor(R.id.widgetText2,android.graphics.Color.BLACK);

			appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		System.out.println("onUpdatep----over");

	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		System.out.println("onDeleted");
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		System.out.println("onDisabled");
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		System.out.println("onEnabled");
		super.onEnabled(context);
	}

	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();
		System.out.println("action is "+action);
		if (UPDATE_ACTION.equals(action)) {
			System.out.println("onReceive--action");
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.schedule_appwidget);
			AppWidgetManager appWidgetManager = AppWidgetManager
					.getInstance(context);
			appWidgetManager = AppWidgetManager.getInstance(context);
			ComponentName mComponentName = new ComponentName(context,
					ScheduleAppWidgetProvider.class); // MarketWidget.classÊÇ×ÔŒºÐŽµÄwidgetµÄÀàÃû¡£
			// mAppWidgetIds = appWidgetManager.getAppWidgetIds(mComponentName);
			this.onUpdate(context, appWidgetManager,
					appWidgetManager.getAppWidgetIds(mComponentName));
			ComponentName componentName = new ComponentName(context,
					ScheduleAppWidgetProvider.class);
			appWidgetManager.updateAppWidget(componentName, remoteViews);
		} else {
			System.out.println("onReceive--noaction");
			super.onReceive(context, intent);
		}
	}
		
}
