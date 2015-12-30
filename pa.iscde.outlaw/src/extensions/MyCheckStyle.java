package extensions;

import java.util.ArrayList;
import java.util.List;

import pa.iscde.checkstyle.extensibility.Check;
import pa.iscde.checkstyle.extensibility.Config;
import pa.iscde.checkstyle.model.SeverityType;
import pa.iscde.checkstyle.model.Violation;
import pa.iscde.checkstyle.model.ViolationDetail;

public class MyCheckStyle extends Check {
	private static final String CHECK_ID = "MinFileLengthCheck";

	/**
	 * Indicates the first line of the file, in which the annotation of this
	 * check, if exists, will appear.
	 */
	private static final int FIRST_LINE = 1;

	/**
	 * Default maximum number of lines.
	 */
	private static final String Min_LINES_KEY = "Min_LINES";
	private static final int Min_LINES = 5;

	/**
	 * The message that should appear in the main report.
	 */
	private static final String MAIN_REPORT_MESSAGE_KEY = "MAIN_REPORT_MESSAGE";
	private static final String MAIN_REPORT_MESSAGE = "File length is shorter than min allowed (%d).";

	/**
	 * The message that should appear in the detailed report.
	 */
	private static final String DETAILED_REPORT_MESSAGE_KEY = "DETAILED_REPORT_MESSAGE";
	private static final String DETAILED_REPORT_MESSAGE = "File length is '%d' lines (min allowed is '%d').";

    public MyCheckStyle() {
        super(CHECK_ID, SeverityType.WARNING);
        setupConfigs();
    }

    @Override
    public Violation process() {
    	this.lines = getFileLines();

		int count = 0;

		final List<ViolationDetail> details = new ArrayList<ViolationDetail>();

		final int minLines = Integer.valueOf(configs.get(Min_LINES_KEY).getValue().toString());

		if (lines.length < minLines) {
			System.out.println("ENTREI");
			final String configuredDetailedMessage = String.valueOf(configs.get(DETAILED_REPORT_MESSAGE_KEY).getValue());
			final String detailedMessage = String.format(configuredDetailedMessage, lines.length, minLines);

			final ViolationDetail violationDetail = new ViolationDetail();
			violationDetail.setSeverity(severity);
			violationDetail.setType(CHECK_ID);
			violationDetail.setResource(resource);
			violationDetail.setLocation(file.getAbsolutePath());
			violationDetail.setMessage(detailedMessage);
			violationDetail.setLine(FIRST_LINE);

			details.add(violationDetail);
			++count;
		}

		if (count == 0) {
			return null;
		}

		final String message = String.valueOf(configs.get(MAIN_REPORT_MESSAGE_KEY).getValue());

		final Violation violation = new Violation();
		violation.setSeverity(severity);
		violation.setType(CHECK_ID);
		violation.setDescription(String.format(message, minLines));
		violation.setCount(count);
		violation.setDetails(details);

		return violation;
    }

    private void setupConfigs() {
        // Adding the default configurations for this check
        this.configs.put(Min_LINES_KEY, new Config(Min_LINES_KEY, Min_LINES, Integer.class));
        this.configs.put(MAIN_REPORT_MESSAGE_KEY, new Config(MAIN_REPORT_MESSAGE_KEY, String.format(MAIN_REPORT_MESSAGE, Min_LINES), String.class));
        this.configs.put(DETAILED_REPORT_MESSAGE_KEY, new Config(DETAILED_REPORT_MESSAGE_KEY, DETAILED_REPORT_MESSAGE, String.class));
    }
}