package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DateRange implements Serializable {

	private Date from;
	private Date to;
	private boolean ignoreTime = true;

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		if (this.isIgnoreTime()) {
			Calendar now = Calendar.getInstance();
			now.setTime(from);
			now.set(Calendar.HOUR_OF_DAY, 0);
			now.set(Calendar.MINUTE, 0);
			now.set(Calendar.SECOND, 0);
			this.from = now.getTime();
		} else {
			this.from = from;
		}
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		if (this.isIgnoreTime()) {
			Calendar now = Calendar.getInstance();
			now.setTime(to);
			now.set(Calendar.HOUR_OF_DAY, 23);
			now.set(Calendar.MINUTE, 59);
			now.set(Calendar.SECOND, 59);
			this.to = now.getTime();
		} else {
			this.to = to;
		}
	}

	public Date getNow() {
		return new Date();
	}

	public boolean isIgnoreTime() {
		return ignoreTime;
	}

	public void setIgnoreTime(boolean ignoreTime) {
		this.ignoreTime = ignoreTime;
	}

	/*
	 * public boolean filterByDate(Object value, Object filter, Locale locale) {
	 * Date colDate = (Date) value; return this.range.getFrom().before(colDate) &&
	 * this.range.getTo().after(colDate); }
	 */
}