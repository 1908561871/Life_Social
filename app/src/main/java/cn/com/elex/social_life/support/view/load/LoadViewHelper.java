/*
Copyright 2015 shizhefei（LuckyJayce）
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
   http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package cn.com.elex.social_life.support.view.load;

import android.view.View;

import com.victor.loading.book.BookLoading;
import com.victor.loading.rotate.RotateLoading;

import cn.com.elex.social_life.R;


/**
 * 自定义要切换的布局，通过IVaryViewHelper实现真正的切换<br>
 * 使用者可以根据自己的需求，使用自己定义的布局样式
 * 
 * @author LuckyJayce
 *
 */
public class LoadViewHelper {

	private IVaryViewHelper helper;

	public LoadViewHelper(View view) {
		this(new VaryViewHelper(view));
	}

	public LoadViewHelper(IVaryViewHelper helper) {
		super();
		this.helper = helper;
	}

	/*public void showError(String errorText, String buttonText, OnClickListener onClickListener) {
		View layout = helper.inflate(R.layout.load_error);
		TextView textView = (TextView) layout.findViewById(R.id.textView1);
		textView.setText(errorText);
		Button button = (Button) layout.findViewById(R.id.button1);
		button.setText(buttonText);
		button.setOnClickListener(onClickListener);
		helper.showLayout(layout);
	}

	public void showEmpty(String errorText, String buttonText, OnClickListener onClickListener) {
		View layout = helper.inflate(R.layout.load_empty);
		TextView textView = (TextView) layout.findViewById(R.id.textView1);
		textView.setText(errorText);
		Button button = (Button) layout.findViewById(R.id.button1);
		button.setText(buttonText);
		button.setOnClickListener(onClickListener);
		helper.showLayout(layout);
	}
*/

	public void showLoading() {
		View layout = helper.inflate(R.layout.load_ing);
		RotateLoading loading= (RotateLoading) layout.findViewById(R.id.bookloading);
		loading.start();
		helper.showLayout(layout);
	}


	public void restore() {
		helper.restoreView();
	}
}