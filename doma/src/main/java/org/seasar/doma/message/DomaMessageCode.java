/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.message;

import java.util.ResourceBundle;

import org.seasar.doma.MessageCode;
import org.seasar.doma.internal.util.MessageFormatter;

/**
 * デフォルトロケール用のメッセージパターンの列挙です。
 * 
 * @author taedium
 * 
 */
public enum DomaMessageCode implements MessageCode {

    // doma
    DOMA0001("パラメータ[{0}]がnullです。"),
    DOMA0002("パラメータ[{0}]が不正です。理由は次のとおりです。{1}。"),

    // wrapper
    DOMA1006("ドメインの値をラップするのに失敗しました。原因は次のものです。{0}"),

    // jdbc
    DOMA2001("SQLの実行結果が1件ではありませんでした。フォーマットされたSQLは次のものです。[{0}]。実際のSQLは次のものです。[{1}]"),
    DOMA2003("楽観的排他制御により更新処理が失敗しました。フォーマットされたSQLは次のものです。[{0}]。実際のSQLは次のものです。[{1}]"),
    DOMA2004("一意制約違反により更新処理が失敗しました。フォーマットされたSQLは次のものです。[{0}]。実際のSQLは次のものです。[{1}]。詳しい原因は次のものです。{2}"),
    DOMA2008("JDBCに関する操作に失敗しました。原因は次のものです。{1}"),
    DOMA2009("SQLの実行に失敗しました。フォーマットされたSQLは次のものです。[{0}]。実際のSQLは次のものです。[{1}]。原因は次のものです。{2}。根本原因は次のものです。{3}"),
    DOMA2010("SQLファイル[{0}]のデータを取得できませんでした。原因は次のものです。{1}"),
    DOMA2011("SQLファイル[{0}]がクラスパスから見つかりませんでした。"),
    DOMA2015("Connectionの取得に失敗しました。原因は次のものです。{0}"),
    DOMA2016("PreparedStatementの取得に失敗しました。原因は次のものです。{0}"),
    DOMA2017("エンティティ[{0}]のIDプロパティの生成に失敗しました。"),
    DOMA2018("エンティティ[{0}]のIDプロパティの生成に失敗しました。原因は次のものです。{1}"),
    DOMA2019("Wrapperクラス[{0}]がJDBCの型にマッピングされていません。"),
    DOMA2020("エンティティ[{0}]のIDプロパティ[{1}]に値が設定されていません。"),
    DOMA2021("エンティティ[{0}]のIDプロパティ[{1}]に自動生成のstrategy[{2}]が指定されていますが、DBMS[{3}]ではサポートされていません。"),
    DOMA2022("IDプロパティのないエンティティ[{0}]の更新や削除はできません。"),
    DOMA2023("悲観的排他制御は、DBMS[{0}]ではサポートされていません。"),
    DOMA2024("テーブル名もしくはカラム名を指定した悲観的排他制御は、DBMS[{0}]ではサポートされていません。"),
    DOMA2025("CallableStatementの取得に失敗しました。原因は次のものです。{0}"),
    DOMA2028("楽観的排他制御によりバッチ更新処理が失敗しました。実際のSQLは次のものです。[{0}]"),
    DOMA2029("一意制約違反によりバッチ更新処理が失敗しました。実際のSQLは次のものです。[{0}]。詳しい原因は次のものです。{1}"),
    DOMA2030("バッチ更新処理の実行に失敗しました。実際のSQLは次のものです。[{0}]。原因は次のものです。{1}。根本原因は次のものです。{2}"),
    DOMA2032("Statementの取得に失敗しました。原因は次のものです。{0}"),
    DOMA2033("インスタンス変数[{0}]が未設定です。"),
    DOMA2034("クラス[{0}]のサポートされてないメソッド[{1}]が呼び出されました。"),
    DOMA2035("Configの実装クラス[{0}]のメソッド[{1}]からnullが返されました。"),
    DOMA2036("不正な呼び出しです。引数なしのコンストラクタは実行できません。"),
    DOMA2101("SQL[{0}]の解析に失敗しました（[{1}]行目[{2}]番目の文字付近）。文字列リテラルの終了を示すクォテーション['']が見つかりません。"),
    DOMA2102("SQL[{0}]の解析に失敗しました（[{1}]行目[{2}]番目の文字付近）。ブロックコメントの終了を示す文字列[*/]が見つかりません。"),
    DOMA2103("SQL[{0}]の解析に失敗しました（[{1}]行目[{2}]番目の文字付近）。--elseifの終了を示す文字列[--]が見つかりません。"),
    DOMA2104("SQL[{0}]の解析に失敗しました（[{1}]行目[{2}]番目の文字付近）。/*%end*/に対応する/*%if ...*/が見つかりません。"),
    DOMA2105("SQL[{0}]の解析に失敗しました（[{1}]行目[{2}]番目の文字付近）。--elseに対応する/*%if ...*/が見つかりません。"),
    DOMA2106("SQL[{0}]の解析に失敗しました（[{1}]行目[{2}]番目の文字付近）。--elseifに対応する/*%if ...*/が見つかりません。"),
    DOMA2107("SQL[{0}]の解析に失敗しました（[{1}]行目[{2}]番目の文字付近）。複数の--elseが存在します。"),
    DOMA2108("SQL[{0}]の解析に失敗しました（[{1}]行目[{2}]番目の文字付近）。--elseの後ろに--elseifが存在します。"),
    DOMA2109("SQL[{0}]の解析に失敗しました（[{1}]行目[{2}]番目の文字付近）。閉じ括弧に対応する開き括弧が見つかりません。"),
    DOMA2110("SQL[{0}]の解析に失敗しました（[{1}]行目[{2}]番目の文字付近）。バインド変数コメント[{3}]の直後にテスト用のリテラルもしくは括弧が見つかりません。"),
    DOMA2111("SQL[{0}]の組み立てに失敗しました（[{1}]行目[{2}]番目の文字付近）。原因は次のものです。{3}"),
    DOMA2112("SQL[{0}]の組み立てに失敗しました（[{1}]行目[{2}]番目の文字付近）。バインド変数コメント[{3}]に対応するオブジェクトの型[{4}]がListインタフェースのサブタイプではありません。"),
    DOMA2115("SQL[{0}]の組み立てに失敗しました（[{1}]行目[{2}]番目の文字付近）。バインド変数コメント[{3}]に対応するListの[{4}]番目の要素がnullです。"),
    DOMA2116("SQL[{0}]の組み立てに失敗しました（[{1}]行目[{2}]番目の文字付近）。埋め込み変数コメント[{3}]にシングルクォテーションが含まれています。"),
    DOMA2117("SQL[{0}]の組み立てに失敗しました（[{1}]行目[{2}]番目の文字付近）。埋め込み変数コメント[{3}]にセミコロンが含まれています。"),
    DOMA2118("SQL[{0}]の組み立てに失敗しました（[{1}]行目[{2}]番目の文字付近）。バインド変数コメント[{3}]を正しく扱えませんでした。原因は次のものです。{4}"),
    DOMA2119("SQL[{0}]の組み立てに失敗しました（[{1}]行目[{2}]番目の文字付近）。ブロックコメントを/*%で開始する場合、つづく文字列はifもしくはendでなければいけません。"),
    DOMA2120("SQL[{0}]の解析に失敗しました（[{1}]行目[{2}]番目の文字付近）。バインド変数コメント[{3}]が定義されていますが、バインド変数が空文字です。"),
    DOMA2121("SQL[{0}]の解析に失敗しました（[{1}]行目[{2}]番目の文字付近）。埋め込み変数コメント[{3}]が定義されていますが、埋め込み変数が空文字です。"),
    DOMA2201("ページング用SQLに変換するには元のSQLにorder by句が指定されている必要があります。"),

    // expression
    DOMA3001("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。クラス[{2}]のメソッド[{3}]の実行に失敗しました。原因は次のものです。{4}"),
    DOMA3002("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。クラス[{2}]のメソッド[{3}]が見つかりませんでした。メソッド名が正しいか確認してください。"),
    DOMA3003("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。変数[{2}]に対応するオブジェクトを解決できませんでした。変数名が正しいか確認してください。"),
    DOMA3004("式[{0}]の解析に失敗しました（[{1}]番目の文字付近）。文字列リテラルの終了を示すダブルクォテーション[\"]が見つかりません。"),
    DOMA3005("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。クラス[{2}]が見つかりませんでした。クラス名が正しいか確認してください。"),
    DOMA3006("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。コンストラクタ[{2}]が見つかりませんでした。コンストラクタのパラメータの数や型が正しいか確認してください。"),
    DOMA3007("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。コンストラクタ[{2}]の実行に失敗しました。原因は次のものです。{3}"),
    DOMA3008("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。比較演算子[{2}]の実行に失敗しました。被演算子のクラスがjava.lang.Comparableを実装していないか、2つの被演算子の型が比較不可能なのかもしれません。原因は次のものです。{3}"),
    DOMA3009("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。比較演算子[{2}]の実行に失敗しました。どちらかの値がnullの場合には、比較できません。"),
    DOMA3010("式[{0}]の解析に失敗しました（[{1}]番目の文字付近）。演算子[{2}]に対する被演算子が見つかりませんでした。"),
    DOMA3011("式[{0}]の解析に失敗しました（[{1}]番目の文字付近）。サポートされていないトークン[{2}]が見つかりました。"),
    DOMA3012("式[{0}]の解析に失敗しました（[{1}]番目の文字付近）。不正な数値リテラル[{2}]が見つかりました。"),
    DOMA3013("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。算術演算子[{2}]の実行に失敗しました。被演算子[{3}]のクラス[{4}]が数値型ではありません。"),
    DOMA3014("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。算術演算子[{2}]の実行に失敗しました。原因は次のものです。{3}"),
    DOMA3015("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。算術演算子[{2}]の実行に失敗しました。被演算子の値がnullです。"),
    DOMA3016("式[{0}]の解析に失敗しました（[{1}]番目の文字付近）。文字リテラルの終了を示すクォテーション['']が見つかりません。文字リテラルの長さは1でなければいけません。"),
    DOMA3018("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。クラス[{2}]のフィールド[{3}]が見つかりませんでした。フィールド名が正しいか確認してください。"),
    DOMA3019("式[{0}]の評価に失敗しました（[{1}]番目の文字付近）。クラス[{2}]のフィールド[{3}]へのアクセスに失敗しました。原因は次のものです。{4}"),

    // apt
    DOMA4001("戻り値の型は更新件数を示すintでなければいけません。"),
    DOMA4002("パラメータの数は1つでなければいけません。"),
    DOMA4003("パラメータは@Entityが注釈されたクラスでなければいけません。"),
    DOMA4005("@Selectや@Updateなど問い合わせの種別を表すアノテーションが必要です。"),
    DOMA4007("戻り値のListの実型引数の型[{0}]はサポートされていません。"),
    DOMA4008("クラス[{0}]はサポートされていません。"),
    DOMA4011("クラス[{0}]のアノテーション処理に失敗しました。原因は次のものです。{1}"),
    DOMA4014("@Daoはインタフェース以外に注釈できません。"),
    DOMA4015("@Entityはクラス以外に注釈できません。"),
    DOMA4016("予期しない例外が発生しました。原因の詳細についてはログ(EclipseならばError Logビュー、javacならばコンソールなど)を確認してください。"),
    DOMA4017("@Daoが注釈されたインタフェースはトップレベルでなければいけません。"),
    DOMA4018("@Entityが注釈されたクラスはトップレベルでなければいけません。"),
    DOMA4019("SQLファイル[{0}]がクラスパスから見つかりませんでした。"),
    DOMA4024("@Versionが重複しています。@Versionが注釈されるフィールドはクラス階層の中で1つでなければいけません。"),
    DOMA4025("[{0}]で始まる名前はDomaにより予約されているため使用できません。"),
    DOMA4026("[{0}]で終わる名前は自動生成されるクラスの名前と重複する可能性があります。"),
    DOMA4028("Listの実型引数の型は基本型もしくは@Domainが注釈されたクラスでなければいけません。"),
    DOMA4034("@GeneratedValueのstrategy要素にGenerationType.SEQUECNEを指定する場合、@SequenceGeneratorの指定も必要です。"),
    DOMA4035("@GeneratedValueのstrategy要素にGenerationType.TABLEを指定する場合、@TableGeneratorの指定も必要です。"),
    DOMA4036("@GeneratedValueを使用する場合、@Idは１つでなければいけません。"),
    DOMA4037("複数の@GeneratedValueが見つかりました。@GeneratedValueは1つでなければいけません。"),
    DOMA4038("EntityLister[{0}]の実型引数の型[{1}]は@Entityが注釈されたクラス[{2}]のスーパータイプでなければいけません。"),
    DOMA4039("コンパイルが失敗している可能性があるためAPTの処理を中止します。コンパイルが失敗している原因については実行環境（Eclipseやjavac）のエラーメッセージを確認してください。このメッセージが生成された箇所を知りたい場合は、ログ(EclipseならばError Logビュー、javacならばコンソールなど)を確認してください。"),
    DOMA4040("戻り値の型は更新件数を示すintの配列でなければいけません。"),
    DOMA4042("型はjava.util.Listでなければいけません。"),
    DOMA4043("Listの実型引数の型は@Entityが注釈されたクラスでなければいけません。"),
    DOMA4045("@Daoが注釈されたインタフェースは他のインタフェースを継承してはいけません。"),
    DOMA4051("@Entityが注釈されたクラスには型パラメータを定義できません。"),
    DOMA4053("SelectOption型のパラメータは複数指定できません。"),
    DOMA4054("IterationCallback型のパラメータは複数指定できません。"),
    DOMA4055("戻り値の型[{0}]とIterationCallbackの1番目の実型引数の型[{1}]が一致していません。"),
    DOMA4056("@Selectのiterate要素にtrueを設定した場合、IterationCallback型のパラメータが必要です。"),
    DOMA4057("IterationCallback型のパラメータを利用するには、@Selectのiteration要素にtrueを設定しなければいけません。"),
    DOMA4059("@Daoが注釈されたインタフェースには型パラメータを定義できません。"),
    DOMA4062("@ResultSetが注釈されたパラメータの型は、java.util.Listでなければいけません。"),
    DOMA4063("@Functionの戻り値として型[{0}]はサポートされていません。"),
    DOMA4064("@Procedureが注釈されたメソッドの戻り値の型はvoidでなければいけません。"),
    DOMA4065("戻り値のListの実型引数の型[{0}]は、サポートされていません。"),
    DOMA4066("@Functionもしくは@Procedureが注釈されたメソッドのパラメータには、@In、@InOut、@Out、@ResultSetのいずれかの注釈が必須です。"),
    DOMA4067("SQL内の変数[{0}]に対応するパラメータがメソッドに存在しません（[{1}]番目の文字付近）。"),
    DOMA4068("SQLファイル[{0}]の読み込みに失敗しました。原因は次のものです。{1}"),
    DOMA4069("SQLファイル[{0}]のパースに失敗しました。原因は次のものです。{1}"),
    DOMA4071("式[{0}]（[{1}]番目の文字付近）に含まれる変数[{2}]（フィールドもしくはメソッドの戻り値の型が[{3}]）からpublicで戻り値を返すメソッド[{4}]が見つかりません。"),
    DOMA4074("メッセージコード[{0}]が次の例外により生成されます。{1}"),
    DOMA4076("パラメータの型は配列型でなければいけません。"),
    DOMA4078("パラメータの数は0でなければいけません。"),
    DOMA4079("クラス[{0}]のソースファイルの生成に失敗しました。原因は次のものです。{1}"),
    DOMA4080("@Delegateのtarget要素で指定したクラス[{0}]に、org.seasar.doma.jdbc.Config型を受けつけるpublicなコンストラクタがみつかりません。"),
    DOMA4081("@Delegateのtarget要素で指定したクラス[{0}]に、このメソッドと同じシグニチャをもつpublicなメソッドがみつかりません。"),
    DOMA4084("include要素で指定したプロパティ[{0}]が、@Entityが注釈されたクラス[{1}]に見つかりません。"),
    DOMA4085("exclude要素で指定したプロパティ[{0}]が、@Entityが注釈されたクラス[{1}]に見つかりません。"),
    DOMA4086("アノテーション[{0}]とアノテーション[{1}]が競合しています。これらは同じメソッドに注釈できません。"),
    DOMA4088("@Idもしくは@Versionを注釈したした場合、@Columnのinsertable要素にfalseを指定してはいけません。"),
    DOMA4089("@Idもしくは@Versionを注釈したした場合、@Columnのupdatable要素にfalseを指定してはいけません。"),
    DOMA4090("注釈プロセッサ[{0}]でクラス[{1}]の処理を開始しました。"),
    DOMA4091("注釈プロセッサ[{0}]でクラス[{1}]の処理を終了しました。"),
    DOMA4092("SQLファイル[{0}]の妥当検査に失敗しました。SQL[{1}]（[{2}]行目[{3}]番目の文字付近）。詳細は次のものです。{4}"),
    DOMA4093("@Versionは数値のプリミティブ型もしくはNumberのサプタイプのプロパティに対してのみ有効です。"),
    DOMA4094("永続対象のフィールドもしくは@ChangedPropertiesが注釈されたフィールドはprivateであってはいけません。"),
    DOMA4096("クラス[{0}]は、永続対象の型としてサポートされていません。"),
    DOMA4097("戻り値のクラスは、[{0}]でなければいけません。"),
    DOMA4098("@Outが注釈されたパラメータの型は、org.seasar.doma.jdbc.Referenceでなければいけません。"),
    DOMA4100("Referenceの実型引数の型[{0}]はサポートされていません。"),
    DOMA4101("@Inが注釈されるパラメータとして型[{0}]はサポートされていません。"),
    DOMA4102("@DomainのvalueType要素に指定された型[{0}]は永続対象の型としてサポートされていません。"),
    DOMA4103("型[{0}]をパラメータにもつ非privateなコンストラクタが見つかりません。"),
    DOMA4104("型[{0}]を戻り値にもったpublicなパラメータなしのメソッド[{1}]が見つかりません。"),
    DOMA4105("@Domainはクラス以外に注釈できません"),
    DOMA4106("@Domainが注釈されたクラスはトップレベルでなければいけません。"),
    DOMA4107("@Domainを注釈したクラスには型パラメータを定義できません。"),
    DOMA4108("Listには[{0}]には実型引数が必須です。"),
    DOMA4109("戻り値のListには[{0}]には実型引数が必須です。"),
    DOMA4110("IterationCallback型のパラメータ[{0}]には型パラメータが必須です。"),
    DOMA4111("@InOutが注釈されたパラメータの型は、org.seasar.doma.jdbc.Referenceでなければいけません。"),
    DOMA4112("パラメータの型[{0}]をワイルカード型にしてはいけません。"),
    DOMA4113("戻り値の型[{0}]をワイルカード型にしてはいけません。"),
    DOMA4114("式[{0}]（[{1}]番目の文字付近）に含まれる変数[{2}]の型[{3}]にインスタンスフィールド[{4}]が見つかりません。"),
    DOMA4115("式[{0}]（[{1}]番目の文字付近）に含まれるコンストラクタ[{2}]が見つかりません。"),
    DOMA4116("式[{0}]（[{1}]番目の文字付近）に含まれる比較演算子[{2}]の左被演算子[{3}]と右被演算子[{4}]の型が異なっています。"),
    DOMA4117("式[{0}]（[{1}]番目の文字付近）に含まれる論理演算子[{2}]の左被演算子[{3}]がboolean/Boolean型ではありません。"),
    DOMA4118("式[{0}]（[{1}]番目の文字付近）に含まれる論理演算子[{2}]の右被演算子[{3}]がboolean/Boolean型ではありません。"),
    DOMA4119("式[{0}]（[{1}]番目の文字付近）に含まれる論理演算子[{2}]の被演算子[{3}]がboolean/Boolean型ではありません。"),
    DOMA4120("式[{0}]（[{1}]番目の文字付近）に含まれる算術演算子[{2}]の左被演算子[{3}]が数値型ではありません。"),
    DOMA4121("式[{0}]（[{1}]番目の文字付近）に含まれる算術演算子[{2}]の右被演算子[{3}]が数値型ではありません。"),
    DOMA4122("SQLファイル[{0}]の妥当検査に失敗しました。メソッドのパラメータ[{1}]がSQLファイルで参照されていません。"),
    DOMA4123("@Entityが注釈されたクラスはprivateであってはいけません。"),
    DOMA4124("@Entityが注釈されたクラスは非privateな引数なしのコンストラクタを持たねばなりません。"),
    DOMA4125("@ChangedPropertiesが重複しています。@ChangedPropertiesが注釈されたフィールドはクラス階層中に1つでなければいけません。"),
    DOMA4126("@Entityが注釈された親クラス[{0}]は同じパッケージに存在していなければいけません。"),
    DOMA4132("@Domainが注釈されたクラスはabstractであってはいけません。"),
    DOMA4133("@Domainが注釈されたクラスはprivateであってはいけません。"),
    DOMA4134("@Entityが注釈されたクラスはabstractであってはいけません。"),
    DOMA4135("@ChangedPropertiesが注釈されたフィールドの型はSet<String>のサブタイプでなければいけません。"),
    DOMA4136("Setには実型引数が必須です。"),
    DOMA4137("@ChangedPropertiesが注釈されたフィールドの型はSet<String>のサブタイプでなければいけません。"),
    DOMA4138("式[{0}]（[{1}]番目の文字付近）に含まれるクラス[{2}]が見つかりません。"), ;

    private static ResourceBundle bundle = ResourceBundle
            .getBundle(DomaMessageResource.class.getName());

    private final String messagePattern;

    private DomaMessageCode(String messagePattern) {
        this.messagePattern = messagePattern;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessagePattern() {
        return messagePattern;
    }

    @Override
    public String getMessage(Object... args) {
        return MessageFormatter.getMessage(this, bundle, args);
    }
}
