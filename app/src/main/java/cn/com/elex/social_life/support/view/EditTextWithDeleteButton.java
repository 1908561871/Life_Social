package cn.com.elex.social_life.support.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import cn.com.elex.social_life.R;

public class EditTextWithDeleteButton extends LinearLayout {
	protected EditText editText;
	protected ImageButton clearTextButton;
	protected boolean isShowDeleteBt;
	protected float textSize;
	protected String digits;
	private boolean  isPassWord;
	public interface TextChangedListener extends TextWatcher{
	}
	TextChangedListener editTextListener = null;
	public void addTextChangedListener(TextChangedListener listener) {
        this.editTextListener = listener;
    }
	public EditTextWithDeleteButton(Context context) {
		super(context);
	}

	public EditTextWithDeleteButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context, attrs);
	}

	public EditTextWithDeleteButton(Context context, AttributeSet attrs, int defStyle) {
		this(context, attrs);
		initViews(context, attrs);
	}

	private void initViews(Context context, AttributeSet attrs) {
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.EditTextWithDeleteButton, 0, 0);
		String hintText;
		int deleteButtonRes;
		try {
			// get the text and colors specified using the names in attrs.xml
			hintText = a.getString(R.styleable.EditTextWithDeleteButton_hintText);
			deleteButtonRes = a.getResourceId(
					R.styleable.EditTextWithDeleteButton_deleteButtonRes,
					R.drawable.text_field_clear_btn);
			isShowDeleteBt=a.getBoolean(R.styleable.EditTextWithDeleteButton_isShowDeleteBt, false);
			textSize=a.getDimensionPixelSize(R.styleable.EditTextWithDeleteButton_textSize, 14);
			digits=a.getString(R.styleable.EditTextWithDeleteButton_digits);
			isPassWord=a.getBoolean(R.styleable.EditTextWithDeleteButton_password, false);
		} finally {
			a.recycle();
		}
		editText = createEditText(context, hintText);
		clearTextButton = createImageButton(context, deleteButtonRes);

		this.addView(editText);
		this.addView(clearTextButton);
		editText.addTextChangedListener(txtEntered());
	

		editText.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus && editText.getText().toString().length() > 0)
					if (isShowDeleteBt) {
						clearTextButton.setVisibility(View.VISIBLE);
					} else
						clearTextButton.setVisibility(View.GONE);

			}
		});
		editText.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				if (hasFocus){
					judgeIfShowDeleteBtn();
				}else{
					clearTextButton.setVisibility(View.GONE);
				}
			}
		});
		clearTextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				editText.setText("");
				clearTextButton.setVisibility(View.GONE);
			}
		});
	}


	public  void judgeIfShowDeleteBtn(){

		if (editText.getText().toString().length() > 0)
		{
			if (isShowDeleteBt){
				clearTextButton.setVisibility(View.VISIBLE);
			}
		}else{
			clearTextButton.setVisibility(View.GONE);
		}

	}

	public TextWatcher txtEntered() {
		return new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				 if (editTextListener != null)
			            editTextListener.onTextChanged(s, start, before, count);
				judgeIfShowDeleteBtn();


			}

			@Override
			public void afterTextChanged(Editable s) {
				if (editTextListener != null)
		            editTextListener.afterTextChanged(s);

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				if (editTextListener != null)
		            editTextListener.beforeTextChanged(s, start, count, after);

			}

		};
	}

	@SuppressLint("NewApi")
	private EditText createEditText(Context context, String hintText) {
		editText = new EditText(context);
		editText.setRawInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		editText.setEllipsize(TextUtils.TruncateAt.END);
		editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
		editText.setLayoutParams(new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
		editText.setLines(1);
		editText.setHorizontallyScrolling(false);
		editText.setVerticalScrollBarEnabled(true);
		editText.setGravity(Gravity.LEFT);
		editText.setBackground(null);
		editText.setHintTextColor(getResources().getColor(R.color.light_gray_text));
		editText.setHint(hintText);
		editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		editText.setTextColor(getResources().getColor(R.color.primary_text));
		if (!TextUtils.isEmpty(digits)){
			editText.setKeyListener(DigitsKeyListener.getInstance(digits));
		}
		if (isPassWord)
		{
			editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		}
		return editText;
	}

	private ImageButton createImageButton(Context context, int deleteButtonRes) {
		clearTextButton = new ImageButton(context);
		LayoutParams params = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		params.gravity = Gravity.CENTER_VERTICAL;
		clearTextButton.setLayoutParams(params);
		clearTextButton.setBackgroundResource(deleteButtonRes);
		clearTextButton.setVisibility(View.GONE);
		return clearTextButton;
	}

	public String getText(){

		return editText.getText().toString().trim();
	}

}