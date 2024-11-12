import { FormEvent, useEffect, useState } from "react"
import { postNumbersList } from "./services"

function App() {
  const [response, setResponse] = useState<string | null>(null)
  const [error, setError] = useState<string | null>(null)
  const [summaryList, setSummaryList] = useState<string[] | null>(null)
  const [numbersListInput, setNumbersListInput] = useState('')

  async function handleFormSubmit(e: FormEvent) {
    e.preventDefault()
    const response = await postNumbersList(numbersListInput)
    setResponse(response)
  }

  useEffect(() => {

    async function handleSummerize() {
      if (!response) return
      const getList = response.split(',')

      if (getList.length > 0 && getList[0].length > 10) {
        setError(response)
        setSummaryList(getList)
      } else {
        setError(null)
        setSummaryList(getList)
      }
    }

    handleSummerize()
  }, [response])

  const renderSummaryList = (
    summaryList?.map((item, index) => (
      <li key={index} className="p-2 border-b border-slate-300 text-slate-700 tracking-wide">{item}</li>
    ))
  )

  const renderSummary = () => (
    <div className="grid place-items-center gap-4 text-center mt-4">
      <h2
        className="text-[18px] font-bold leading-4 text-slate-700"
      >Here is your Numbers Range Summary</h2>

      {
        summaryList && summaryList.length > 0 ? (
          <ul className="mt-4 flex flex-wrap items-center justify-between w-full">
            {renderSummaryList}
          </ul>
        ) : (
          <p className="text-slate-700">Enter a list in the input above 😀</p>
        )
      }
    </div>
  )

  return (
    <main className="min-h-screen overflow-hidden grid place-items-center bg-slate-200 px-6 py10">

      <section className="w-full max-w-[640px] grid gap-4">

        <h1 className="text-center font-extrabold text-[24px] text-slate-800 mb-10">Number Ranges Summerizer 💯</h1>

        <form className="flex flex-col">
          <input
            type="text"
            name="numbers-list"
            id="numbers-list"
            placeholder="Numbers List Range"
            value={numbersListInput}
            className={`p-2 mt-2 border-2 outline-none ${error ? 'border-red-500' : 'border-slate-300 focus:border-blue-300'}  rounded-md transition-colors ease-in-out`}
            onChange={e => setNumbersListInput(e.target.value)} />

          <button
            className={`p-2 mt-2 ${numbersListInput ? 'bg-blue-500' : 'bg-slate-500'} text-white rounded-md transition-colors ease-in-out`}
            onClick={handleFormSubmit}
            type="submit">Summarize</button>
        </form>


        {
          error ? (
            <p className="mt-4 text-center text-[15px] text-red-600">{error}</p>
          ) : (
            renderSummary()
          )
        }

      </section>

    </main>
  )
}

export default App