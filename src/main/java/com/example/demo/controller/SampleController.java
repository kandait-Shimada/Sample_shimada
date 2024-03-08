package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Sample;
import com.example.demo.form.SampleForm;
import com.example.demo.service.SampleService;

@RequestMapping("/sample")
@Controller
public class SampleController {

	@Autowired
	SampleService service;
	
	/* 「form-backing bean」の初期化 */
	@ModelAttribute
	public SampleForm setUpForm() {
		SampleForm form = new SampleForm();
		form.setGender(0);
		return form;
	}
	
	/* 一覧表示 */
	@GetMapping
	public String ShowList(SampleForm sampleForm, Model model) {
		sampleForm.setNewSample(true); // 新規登録設定(初期値をtrueとしておく) 
		Iterable<Sample> sample = service.SelectAll();  // DB内のデータを全件取得
		model.addAttribute("list", sample);
		return "show";
	}
	
	
	
	/* 登録機能 */
	@PostMapping("/insert")
	public String insert(@Validated SampleForm sampleForm, BindingResult bindingResult,
			 Model model, RedirectAttributes redirectAttributes) {
		// formからEntityに
		Sample sample = new Sample();
		//idの自動生成実装で削除
		//sample.setId(sampleForm.getId());
		sample.setName(sampleForm.getName());
		sample.setGender(sampleForm.getGender());
		sample.setAge(sampleForm.getAge());
		//入力チェック
		if( !bindingResult.hasErrors()) {
			service.InsertSample(sample);
			// メッセージ追加
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました！"); 
			return "redirect:/sample";
		} else	{
			//エラー発生時に一覧表示をする
			return ShowList(sampleForm, model);
		}
	}
	
	/* 更新用画面表示 */
	@GetMapping("/{id}")
	public String ShowUpdate(SampleForm sampleForm, @PathVariable Integer id, Model model) {
		//表示する行の取得
		Optional<Sample> sampleOpt = service.SlectOneById(id);
		//SampleFormに入れなおす
		Optional<SampleForm> sampleFormOpt = sampleOpt.map(t -> makeSampleForm(t));
		//SampleFormがnullじゃないときに中身を取り出す
		if(sampleFormOpt.isPresent()) {
			sampleForm = sampleFormOpt.get();
		}
		makeUpdateModel(sampleForm, model);
		return "show";
		
	}

	/* 更新用Model作成 */
	private void makeUpdateModel(SampleForm sampleForm, Model model) {
		//idの自動生成実装で削除
		//model.addAttribute("id", sampleForm.getId());
		sampleForm.setNewSample(false);
		model.addAttribute("sampleForm", sampleForm);
	}

	/* idをキーにしてデータを更新する */
	@PostMapping("/update")
	public String update(@Validated SampleForm sampleForm, BindingResult result,
						 Model model, RedirectAttributes redirectAttributes) {
		Sample sample = MakeSample(sampleForm);
		if(!result.hasErrors()) {
			service.UpdateSample(sample);
            redirectAttributes.addFlashAttribute("complete", "更新が完了しました！"); // 追加
			return "redirect:/sample/" + sample.getId();
		} else {
			makeUpdateModel(sampleForm, model);
			return "show";
		}
	}
	
	private Sample MakeSample(SampleForm sampleForm) {
		Sample sample = new Sample();
		//idの自動生成実装で削除
		//sample.setId(sampleForm.getId());
		sample.setName(sampleForm.getName());
		sample.setGender(sampleForm.getGender());
		sample.setAge(sampleForm.getAge());
		return sample;
	}
	
	/*
	 * makeQuizFormはquiz(エンティティクラス(DB関連クラス))からQuizForm(Formクラス)へ
	 * 値を代入しformを返している。
	 */
	private SampleForm makeSampleForm(Sample sample) {
		SampleForm form = new SampleForm();
		//idの自動生成実装で削除
		//form.setId(sample.getId());
		form.setName(sample.getName());
		form.setGender(sample.getGender());
		form.setAge(sample.getAge());
		form.setNewSample(false);
		return form;
	}

	//削除機能
	@PostMapping("/delete")
	public String delete(@RequestParam("id") String id, Model model,
			RedirectAttributes redirectAttributes) {
		service.DeleteSampleById(Integer.parseInt(id));
		
		//メッセージ追加
		redirectAttributes.addFlashAttribute("complete", "削除が完了しました！"); 
		return "redirect:/sample";
	}

}















